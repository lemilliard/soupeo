package fr.epsi.i4.soupeoserver.analyzer;

import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.soupeoserver.analyzer.decisiontree.PageEnum;
import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.faceapi.FaceAPIClient;
import fr.epsi.i4.soupeoserver.mapper.Mapper;
import fr.epsi.i4.soupeoserver.model.apiModel.Verification;
import fr.epsi.i4.soupeoserver.model.morphia.Emotion;
import fr.epsi.i4.soupeoserver.model.morphia.Parcours;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.WebUtils;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.analyseDecisionTree;
import static fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult.*;

/**
 * @author Thomas Kint
 */
public class Analyzer {

	private static Integer cpt = 0;
	private static String previousId;

	public static AnalyzerResult analyze(int index, byte[] image) {
		AnalyzerResult result;
		UserSession userSession = UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
		Object faceAPIResponse = FaceAPIClient.getResponse(image);
		if (checkNewUser(faceAPIResponse, index)) {
			result = SWITCH;
		} else {
			if (userSession.getParcours().get(index).isHelp_used()) {
				result = OK;
			} else {
				PageEnum pagePrecedente = getPagePrecedente(index, userSession.getParcours());
				PageEnum pageActuelle = getPageActuelle(index, userSession.getParcours());
				int nombreVisites = getNombreVisites(index, userSession.getParcours());
				int scoreEmotion = getEmotionScore(faceAPIResponse);

				System.out.println("ScoreEmotion: " + scoreEmotion);

				String decision = analyseDecisionTree.analyze(pagePrecedente, pageActuelle, nombreVisites, scoreEmotion);

				result = AnalyzerResult.valueOf(decision);

				if (result.equals(ASSISTANT)) {
					userSession.getParcours().get(index).setHelp_used(true);
					MainDAO.save(userSession);
				}
			}
		}

		return result;
	}

	private static PageEnum getPageActuelle(int index, List<Parcours> parcours) {
		String url = parcours.get(index).getUrl();
		return Mapper.urlToPageEnum(url);
	}

	private static PageEnum getPagePrecedente(int index, List<Parcours> parcours) {
		if (index > 0) {
			String url = parcours.get(index - 1).getUrl();
			return Mapper.urlToPageEnum(url);
		}
		return PageEnum.PORTAIL;
	}

	private static int getNombreVisites(int index, List<Parcours> parcours) {
		Parcours parcour = parcours.get(index);
		int count = 0;
		for (Parcours p : parcours) {
			if (p.getUrl().equals(parcour.getUrl())) {
				count++;
			}
		}
		return count;
	}

	private static boolean checkNewUser(Object faceAPIResponse, int index) {
		if (faceAPIResponse instanceof ArrayList) {
			ArrayList<LinkedTreeMap> objectArray = (ArrayList<LinkedTreeMap>) faceAPIResponse;

			if (!objectArray.isEmpty()) {
				String faceId = (String) objectArray.get(0).get("faceId");

				if (previousId != null) {
					Verification ver = FaceAPIClient.getVerificationResponse(faceId, previousId);
					if (ver != null && ver.isIdentical() && ver.getConfidence() > 0.70) {
						previousId = faceId;
						cpt = 0;
					} else {
						cpt++;
					}
				} else {
					previousId = faceId;
				}
				if (cpt > 2) {
					endSession(index);
					cpt = 0;
					return true;
				}
			} else {
				cpt++;
			}
		}
		return false;
	}

	private static int getEmotionScore(Object faceAPIResponse) {
		int score = 0;

		if (faceAPIResponse instanceof ArrayList) {
			ArrayList<LinkedTreeMap> objectArray = (ArrayList<LinkedTreeMap>) faceAPIResponse;

			if (!objectArray.isEmpty()) {
				LinkedTreeMap faceAttributes = (LinkedTreeMap) objectArray.get(0).get("faceAttributes");
				LinkedTreeMap emotionTreeMap = (LinkedTreeMap) faceAttributes.get("emotion");

				Emotion emotion = Emotion.fromTreeMap(emotionTreeMap);
				score = emotion.calculateScore();
			}
		}
		return score;
	}

	public static void endSession(int index) {
		UserSession userSession = getCurrentSession();
		if (userSession != null) {
			System.out.println("Closing session: " + userSession.get_id().toHexString());
			userSession.end();
			MainDAO.save(userSession);
			Parcours current = userSession.getParcours().get(index);
			UserSession newSession = new UserSession(WebUtils.getClientIp());
			newSession.getParcours().add(current);
			ObjectId id = MainDAO.save(newSession).get_id();
			System.out.println("StartSession: " + id.toHexString());
		}
	}

	private static UserSession getCurrentSession() {
		return UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
	}
}
