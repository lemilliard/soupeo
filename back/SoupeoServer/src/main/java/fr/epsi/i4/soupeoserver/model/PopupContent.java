package fr.epsi.i4.soupeoserver.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class PopupContent {

    @Id
    private int id_popup;
    private String title;
    private String message;

    public PopupContent(int id_popup, String title, String message) {
        this.setId_popup(id_popup);
        this.setTitle(title);
        this.setMessage(message);
    }

    protected int getId_popup() {
        return id_popup;
    }

    protected void setId_popup(int id_popup) {
        this.id_popup = id_popup;
    }

    protected String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }
}
