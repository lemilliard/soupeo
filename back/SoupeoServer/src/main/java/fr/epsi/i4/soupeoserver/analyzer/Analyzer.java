package fr.epsi.i4.soupeoserver.analyzer;

import com.google.gson.internal.LinkedTreeMap;
import fr.decisiontree.model.Result;
import fr.epsi.i4.soupeoserver.SoupeoServerApplication;
import fr.epsi.i4.soupeoserver.analyzer.decisiontree.CamEnum;
import fr.epsi.i4.soupeoserver.analyzer.decisiontree.PageEnum;
import fr.epsi.i4.soupeoserver.faceapi.FaceAPIClient;
import fr.epsi.i4.soupeoserver.model.Emotion;

import java.util.ArrayList;
import java.util.HashMap;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.analyseDecisionTree;

/**
 * @author Thomas Kint
 */
public class Analyzer {

	public static AnalyzerResult analyze(int id, byte[] image) {
		Object faceAPIResponse = FaceAPIClient.getResponse(image);

		if (faceAPIResponse instanceof ArrayList) {
			ArrayList<LinkedTreeMap> objectArray = (ArrayList<LinkedTreeMap>) faceAPIResponse;

			if (!objectArray.isEmpty()) {
				LinkedTreeMap faceAttributes = (LinkedTreeMap) objectArray.get(0).get("faceAttributes");
				LinkedTreeMap emotionTreeMap = (LinkedTreeMap) faceAttributes.get("emotion");

				Emotion emotion = Emotion.fromTreeMap(emotionTreeMap);
				int score = emotion.calculateScore();
				analyseDecisionTree.analyze(PageEnum.PORTAIL, PageEnum.CV, 2, score);
			}
		}

		return AnalyzerResult.ARDUINO;
	}
}
