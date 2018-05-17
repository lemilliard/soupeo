package fr.epsi.i4.soupeoserver.analyzer;

import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.soupeoserver.analyzer.decisiontree.PageEnum;
import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.faceapi.FaceAPIClient;
import fr.epsi.i4.soupeoserver.mapper.Mapper;
import fr.epsi.i4.soupeoserver.model.apiModel.Emotion;
import fr.epsi.i4.soupeoserver.model.apiModel.Face;
import fr.epsi.i4.soupeoserver.model.apiModel.Verification;
import fr.epsi.i4.soupeoserver.model.morphia.Parcours;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.WebUtils;

import java.util.ArrayList;

import java.util.List;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.analyseDecisionTree;
import static fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult.ASSISTANT;
import static fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult.OK;

/**
 * @author Thomas Kint
 */
public class Analyzer {


	public static AnalyzerResult analyze(int index, byte[] image) {
		AnalyzerResult result;
		UserSession userSession = UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
		if (userSession.getParcours().get(index).isHelp_used()) {
			result = OK;
		} else {
			PageEnum pagePrecedente = getPagePrecedente(index, userSession.getParcours());
			PageEnum pageActuelle = getPageActuelle(index, userSession.getParcours());
			int nombreVisites = getNombreVisites(index, userSession.getParcours());
			int scoreEmotion = getEmotionScore(image);

			String decision = analyseDecisionTree.analyze(pagePrecedente, pageActuelle, nombreVisites, scoreEmotion);

			result = AnalyzerResult.valueOf(decision);

			if (result.equals(ASSISTANT)) {
				userSession.getParcours().get(index).setHelp_used(true);
				MainDAO.save(userSession);
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

	private static Integer cpt = 0;
    private static String previousId;
	private static int getEmotionScore(byte[] image) {
		Object faceAPIResponse = FaceAPIClient.getResponse(image);

		int score = 0;

		if (faceAPIResponse instanceof ArrayList) {
			ArrayList<LinkedTreeMap> objectArray = (ArrayList<LinkedTreeMap>) faceAPIResponse;

			if (!objectArray.isEmpty()) {
			    LinkedTreeMap faceId = (LinkedTreeMap) objectArray.get(0).get("faceId");
				LinkedTreeMap faceAttributes = (LinkedTreeMap) objectArray.get(0).get("faceAttributes");
				LinkedTreeMap emotionTreeMap = (LinkedTreeMap) faceAttributes.get("emotion");

				Emotion emotion = Emotion.fromTreeMap(emotionTreeMap);
                Face face = Face.fromTreeMap(faceId);
				score = emotion.calculateScore();
                if (cpt == 0 || cpt > 2) {
                    previousId = face.getId();
                    cpt = 0;
                    //déconnexion si > 2
                }
                if (previousId != null) {

                    Verification ver = FaceAPIClient.getVerificationResponse(face.getId(), previousId);

                    if (ver.isIdentical() && ver.getConfidence() > 0.70) {
                        previousId = face.getId();
                    }
                    else {
                        cpt++;
                    }
                }
			}
		}
		return score;
	}

}
