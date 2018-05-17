package fr.epsi.i4.soupeoserver.model.apiModel;

import com.google.gson.internal.LinkedTreeMap;

public class Gender {

    private String gender;

    public Gender(String gender) {
        this.gender = gender;
    }

    public static Gender fromTreeMap(LinkedTreeMap treeMap) {
        String gender = (String) treeMap.get("gender");
        return new Gender(gender);
    }
}
