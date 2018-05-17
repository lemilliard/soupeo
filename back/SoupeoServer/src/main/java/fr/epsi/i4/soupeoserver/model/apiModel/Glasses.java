package fr.epsi.i4.soupeoserver.model.apiModel;

import com.google.gson.internal.LinkedTreeMap;

public class Glasses {

    private String glasses;

    public Glasses(String glasses) {
        this.glasses = glasses;
    }

    public static Glasses fromTreeMap(LinkedTreeMap treeMap) {
        String glasses = (String) treeMap.get("glasses");
        return new Glasses(glasses);
    }
}
