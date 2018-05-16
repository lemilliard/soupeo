package fr.epsi.i4.soupeoserver.model.morphia;

import fr.epsi.i4.soupeoserver.model.morphia.FaceAttributes;

import java.util.ArrayList;

class Mood {

	private String date;

	private ArrayList<FaceAttributes> faceAttributes;

	public Mood() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<FaceAttributes> getFaceAttributes() {
		return faceAttributes;
	}

	public void setFaceAttributes(ArrayList<FaceAttributes> faceAttributes) {
		this.faceAttributes = faceAttributes;
	}
}