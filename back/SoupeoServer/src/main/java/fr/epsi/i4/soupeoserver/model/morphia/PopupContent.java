package fr.epsi.i4.soupeoserver.model.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("popup_content")
public class PopupContent {

	@Id
	private ObjectId _id;
	private int id_popup;
	private String title;
	private String message;
	private String url;
	private String paramUrl;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}


}
