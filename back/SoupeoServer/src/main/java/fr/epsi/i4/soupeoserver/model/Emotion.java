package fr.epsi.i4.soupeoserver.model;

import com.google.gson.internal.LinkedTreeMap;

/**
 * @author Thomas Kint
 */
public class Emotion {

	private Double anger;

	private Double disgust;

	private Double fear;

	public Emotion(Double anger, Double disgust, Double fear) {
		this.anger = anger;
		this.disgust = disgust;
		this.fear = fear;
	}

	public static Emotion fromTreeMap(LinkedTreeMap treeMap) {
		Double anger = (Double) treeMap.get("anger");
		Double disgust = (Double) treeMap.get("disgust");
		Double fear = (Double) treeMap.get("fear");
		return new Emotion(anger, disgust, fear);
	}
}
