package fr.epsi.i4.soupeoserver.model.apiModel;

import com.google.gson.internal.LinkedTreeMap;

public class Age {

    private Double age;

    public Age(Double age) {
        this.age = age;
    }

    public static Age fromTreeMap(LinkedTreeMap treeMap) {
        Double age = (Double) treeMap.get("age");
        return new Age(age);
    }
}
