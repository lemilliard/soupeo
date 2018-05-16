package fr.epsi.i4.soupeoserver.model;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
class FaceAttributes {

    private float anger;
    private float disgust;
    private float fear;

    FaceAttributes(float anger, float disgust, float fear) {
        this.setAnger(anger);
        this.setDisgust(disgust);
        this.setFear(fear);
    }

    protected float getAnger() {
        return anger;
    }

    protected void setAnger(float anger) {
        this.anger = anger;
    }

    protected float getDisgust() {
        return disgust;
    }

    protected void setDisgust(float disgust) {
        this.disgust = disgust;
    }

    protected float getFear() {
        return fear;
    }

    protected void setFear(float fear) {
        this.fear = fear;
    }
}