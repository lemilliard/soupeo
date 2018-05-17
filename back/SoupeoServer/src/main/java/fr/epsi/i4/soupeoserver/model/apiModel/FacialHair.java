package fr.epsi.i4.soupeoserver.model.apiModel;

import com.google.gson.internal.LinkedTreeMap;

public class FacialHair {
    private Double sideburns;
    private Double beard;
    private Double moustache;

    public FacialHair(Double sideburns, Double beard, Double moustache) {
        this.sideburns = sideburns;
        this.beard = beard;
        this.moustache = moustache;
    }

    public static FacialHair fromTreeMap(LinkedTreeMap treeMap) {
        Double sideburns = (Double) treeMap.get("sideburns");
        Double beard = (Double) treeMap.get("beard");
        Double moustache = (Double) treeMap.get("moustache");
        return new FacialHair(sideburns, beard, moustache);
    }
}
