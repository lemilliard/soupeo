package fr.epsi.i4.soupeoserver.model;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class UserSession {
	private ObjectId _id;
	private String id_terminal;

	private String location;
	private String start_session;
	private String end_session;

	private ArrayList<Parcours> parcours;

	private ArrayList<IndicePb> indice_pb;

	private ArrayList<Mood> mood;

	public UserSession() {
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getId_terminal() {
		return id_terminal;
	}

	public void setId_terminal(String id_terminal) {
		this.id_terminal = id_terminal;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStart_session() {
		return start_session;
	}

	public void setStart_session(String start_session) {
		this.start_session = start_session;
	}

	public String getEnd_session() {
		return end_session;
	}

	public void setEnd_session(String end_session) {
		this.end_session = end_session;
	}

	public ArrayList<Parcours> getParcours() {
		return parcours;
	}

	public void setParcours(ArrayList<Parcours> parcours) {
		this.parcours = parcours;
	}

	public ArrayList<IndicePb> getIndice_pb() {
		return indice_pb;
	}

	public void setIndice_pb(ArrayList<IndicePb> indice_pb) {
		this.indice_pb = indice_pb;
	}

	public ArrayList<Mood> getMood() {
		return mood;
	}

	public void setMood(ArrayList<Mood> mood) {
		this.mood = mood;
	}
}
