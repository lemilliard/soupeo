package fr.epsi.i4.soupeoserver.model.morphia;

public class PopupContent {

	private int id_popup;
	private String title;
	private String message;

	public PopupContent() {
	}

	public int getId_popup() {
		return id_popup;
	}

	public void setId_popup(int id_popup) {
		this.id_popup = id_popup;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
