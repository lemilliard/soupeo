package fr.epsi.i4.soupeoserver.analyzer;

import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.soupeoserver.analyzer.decisiontree.PageEnum;
import fr.epsi.i4.soupeoserver.faceapi.FaceAPIClient;
import fr.epsi.i4.soupeoserver.model.Emotion;

import java.util.ArrayList;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.analyseDecisionTree;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.mapper.Mapper;
import fr.epsi.i4.soupeoserver.model.morphia.Parcours;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.WebUtils;
import java.util.List;

/**
 * @author Thomas Kint
 */
public class Analyzer {

	public static AnalyzerResult analyze(int index, byte[] image) {
                UserSession userSession = UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
		PageEnum pagePrecedente = getPagePrecedente(index, userSession.getParcours());
		PageEnum pageActuelle = getPageActuelle(index, userSession.getParcours());
		int nombreVisites = getNombreVisites(index, userSession.getParcours());
		int scoreEmotion = getEmotionScore(image);

		String decision = analyseDecisionTree.analyze(pagePrecedente, pageActuelle, nombreVisites, scoreEmotion);

		return AnalyzerResult.valueOf(decision);
	}

	private static PageEnum getPageActuelle(int index, List<Parcours> parcours) {
		String url = parcours.get(index).getUrl();
                return Mapper.urlToPageEnum(url);
	}

	private static PageEnum getPagePrecedente(int index, List<Parcours> parcours) {
                if(index > 0){
                    String url = parcours.get(index - 1).getUrl();
                    return Mapper.urlToPageEnum(url);
                } 
                return null;
	}

	private static int getNombreVisites(int index, List<Parcours> parcours) {
		Parcours parcour = parcours.get(index);
                int count = 0;
                for (Parcours p : parcours){
                    if(p.getUrl().equals(parcour.getUrl())){
                        count ++;
                    }
                }
                return count;
	}

	private static int getEmotionScore(byte[] image) {
		Object faceAPIResponse = FaceAPIClient.getResponse(image);

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
}
