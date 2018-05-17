package fr.epsi.i4.soupeoserver.model.apiModel;

import com.google.gson.internal.LinkedTreeMap;

public class Face {

    private String id;

    public Face(String id) {
        this.id = id;
    }


    public static Face fromTreeMap(LinkedTreeMap treeMap) {
        String id = (String) treeMap.get("faceId");
        return new Face(id);
    }

    public String getId() {
        return id;
    }
}
