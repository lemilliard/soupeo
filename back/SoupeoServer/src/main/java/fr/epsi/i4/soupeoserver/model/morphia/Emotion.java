package fr.epsi.i4.soupeoserver.model.morphia;

import com.google.gson.internal.LinkedTreeMap;
import org.mongodb.morphia.annotations.Embedded;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@Embedded
public class Emotion {

	private Double anger;

	private Double disgust;

	private Double fear;

	public Emotion() {
	}

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

	public int calculateScore() {
		BigDecimal score = new BigDecimal(anger);
		score = score.add(new BigDecimal(disgust));
		score = score.add(new BigDecimal(fear));
		score = score.multiply(new BigDecimal(100));
		return score.intValue();
	}
}
