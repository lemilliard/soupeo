package fr.epsi.i4.soupeoserver.model.morphia;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Mood {

	private String date;
	private String url;

	private Emotion emotion;

	public Mood() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Emotion getEmotion() {
		return emotion;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}
}