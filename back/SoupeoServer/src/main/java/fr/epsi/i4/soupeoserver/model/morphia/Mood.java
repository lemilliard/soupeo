package fr.epsi.i4.soupeoserver.model.morphia;

import java.util.ArrayList;

class Mood {

	private String date;
	private String url;

	private ArrayList<FaceAttributes> faceAttributes;

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

	public ArrayList<FaceAttributes> getFaceAttributes() {
		return faceAttributes;
	}

	public void setFaceAttributes(ArrayList<FaceAttributes> faceAttributes) {
		this.faceAttributes = faceAttributes;
	}
}