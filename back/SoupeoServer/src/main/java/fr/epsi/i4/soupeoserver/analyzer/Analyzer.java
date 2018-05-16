package fr.epsi.i4.soupeoserver.analyzer;

import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.soupeoserver.analyzer.decisiontree.PageEnum;
import fr.epsi.i4.soupeoserver.faceapi.FaceAPIClient;
import fr.epsi.i4.soupeoserver.model.Emotion;

import java.util.ArrayList;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.analyseDecisionTree;

/**
 * @author Thomas Kint
 */
public class Analyzer {

	public static AnalyzerResult analyze(int index, byte[] image) {
		PageEnum pagePrecedente = getPagePrecedente();
		PageEnum pageActuelle = getPageActuelle();
		int nombreVisites = getNombreVisites();
		int scoreEmotion = getEmotionScore(image);

		String decision = analyseDecisionTree.analyze(pagePrecedente, pageActuelle, nombreVisites, scoreEmotion);

		return AnalyzerResult.valueOf(decision);
	}

	private static PageEnum getPageActuelle() {
		return PageEnum.PORTAIL;
	}

	private static PageEnum getPagePrecedente() {
		return PageEnum.CV;
	}

	private static int getNombreVisites() {
		return 2;
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
