package fr.epsi.i4.soupeoserver.model;

import org.mongodb.morphia.annotations.Embedded;

import java.util.ArrayList;
import java.util.Date;

@Embedded
class Mood {

    private Date date;
    @Embedded
    private ArrayList<FaceAttributes> faceAttributes;

    Mood(Date date, ArrayList<FaceAttributes> faceAttributes) {
        this.setDate(date);
        this.setFaceAttributes(faceAttributes);
    }

    protected Date getDate() {
        return date;
    }

    protected void setDate(Date date) {
        this.date = date;
    }

    protected ArrayList<FaceAttributes> getFaceAttributes() {
        return faceAttributes;
    }

    protected void setFaceAttributes(ArrayList<FaceAttributes> faceAttributes) {
        this.faceAttributes = faceAttributes;
    }
}