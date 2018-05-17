package fr.epsi.i4.soupeoserver.model.apiModel;

public class Verification {

    private boolean isIdentical;
    private Double confidence;

    public boolean isIdentical() {
        return isIdentical;
    }

    public void setIdentical(boolean identical) {
        isIdentical = identical;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

}
