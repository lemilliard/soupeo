/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.soupeoserver.decisionTree;

/**
 *
 * @author kbouzan
 */
public enum CamEnum {
    NORMAL(0),
    ELEVE(70),
    TRES_ELEVE(85);

    private int value;

    CamEnum(int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }

    public static CamEnum getCamEnum(final int val) {
        final CamEnum[] values = CamEnum.values();
        int min = 0;
        int max = values.length - 1;
        int i = (min + max) / 2;
        while (val != values[i].value && min < max) {
            final int av = values[i].value;
            if (val < av) {
                max = i - 1;
            } else if (val > av) {
                if (i + 1 < values.length && val < values[i + 1].value) {
                    break;
                }
                min = i + 1;
            }
            i = (min + max) / 2;
        }
        if (min == max) {
            return values[max];
        }
        return values[i];
    }
}
