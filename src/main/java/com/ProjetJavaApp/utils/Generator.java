package com.ProjetJavaApp.utils;

import java.time.Year;

public class Generator {

    public static String generateMatricule(String promoLibelle, String referentielLibelle, long apprenantCount) {
        String[] promoTabs = promoLibelle.split(" ");
        StringBuilder promoPrefix = new StringBuilder();
        for (String promoTab : promoTabs) {
            promoPrefix.append(promoTab.substring(0, 1).toUpperCase());
        }
        String[] referentielTabs = referentielLibelle.split(" ");
        StringBuilder referentielPrefix = new StringBuilder();
        if (referentielTabs.length >= 2) {
            referentielPrefix.append(referentielTabs[0].substring(0, 3).toUpperCase())
                    .append(referentielTabs[1].substring(0, 3).toUpperCase());
        } else {
            referentielPrefix.append(referentielTabs[0].substring(0, 3).toUpperCase());
        }
        String date = Year.now().toString() + "_" + (apprenantCount + 1);
        return promoPrefix + "_" + referentielPrefix + "_" + date;
    }


    public static String generateCodeReferentiel(String referentielLibelle) {
        String[] referentielTabs = referentielLibelle.split(" ");
        StringBuilder referentielPrefix = new StringBuilder();
        if (referentielTabs.length >= 2) {
            referentielPrefix.append(referentielTabs[0].substring(0, 3).toUpperCase())
                    .append("-"+referentielTabs[1].substring(0, 3).toUpperCase());
        } else {
            referentielPrefix.append(referentielTabs[0].substring(0, 3).toUpperCase());
        }
        return String.valueOf(referentielPrefix);
    }
}
