/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.soupeoserver.assistant;

import fr.epsi.i4.soupeoserver.mapper.Mapper;
import fr.epsi.i4.soupeoserver.mapper.Word;
import fr.epsi.i4.soupeoserver.mapper.WordType;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kbouzan
 */
public class AssistantVirtuel {

    public int resultAide(Word wordAction, Word wordPage) {
        switch (wordAction) {
            case REDIRECTION:
                switch (wordPage) {
                    case RECHERCHE_EMPLOI:
                        return 0;
                    case RECHERCHE_FORMATION:
                        return 1;
                    case MON_COMPTE:
                        return 2;
                }
                break;
            case CONTACTER:
                switch (wordPage) {
                    case ASSISTANT:
                        return 3;
                }
                break;
        }
        return -1;
    }

    public void resultatAide(String string) {
        Word page = Mapper.extractWordByType(string, WordType.PAGE);
        Word action = Mapper.extractWordByType(string, WordType.ACTION);
        Word interrogatif = Mapper.extractWordByType(string, WordType.INTERROGATION);
        Word ville = Mapper.extractWordByType(string, WordType.VILLE);
        String villeParam = null;
        if (ville != null) {
            villeParam = ville.getWord();
        }
        int idPopup = resultAide(action, page);
        if (interrogatif != null) {
            // on affiche la popup qui correspond à l'id recupere
        } else {
            String params = "";
            // on redirige vers l'url en ajoutant les params s'il existe
            if (idPopup == 0) {
                List<Word> emplois = Mapper.extractWordsByType(string, WordType.EMPLOI);
                String emploisString = "";
                for (int i = 0; i < emplois.size(); i++) {
                    emploisString += emplois.get(i).getWord();
                    if (i + 1 < emplois.size()) {
                        emploisString += ",";
                    }
                }
                params = ajoutParamsRechercheEmploi("?lieux={lieux}&motsCles={motsCles}", emploisString, villeParam);
            } else if (idPopup == 1) {
                Word formation = Mapper.extractWordByType(string, WordType.FORMATION);
                String formationParam = null;
                if(formation != null){
                    formationParam = formation.getWord();
                }
                params = ajoutParamsRechercheFormation("?formationCPFPublicConcerne=Tout+public&ou=COMMUNE-{lieux}&quoi=FORMACODE-{formacode}&range=0-9&tri=0", formationParam, villeParam);
            } else {
                // redirection vers la page
            }
        }
    }

    public String ajoutParamsRechercheEmploi(String paramsUrl, String emploi, String ville) {
        paramsUrl = paramsUrl.replace("?", "");
        String[] test = paramsUrl.split("&");
        String params = "?";
        List<String> testArray = Arrays.asList(test);
        for (String s : testArray) {
            if (s.contains("{lieux}")) {
                if (ville != null) {
                    s = s.replace("{lieux}", ville);
                    params += s;
                }
            }
            if (s.contains("{motsCles}")) {
                if (emploi != null) {
                    s = s.replace("{motsCles}", emploi);
                    params += s;
                }
            }
        }
        System.out.println(params);
        return params;
    }

    public String ajoutParamsRechercheFormation(String paramsUrl, String formation, String ville) {
        paramsUrl = paramsUrl.replace("?", "");
        String[] test = paramsUrl.split("&");
        String params = "?";
        List<String> testArray = Arrays.asList(test);
        for (String s : testArray) {
            if (s.contains("{lieux}")) {
                if (ville != null) {
                    s = s.replace("{lieux}", ville);
                    params += s;
                }
            }
            if (s.contains("{formacode}")) {
                if (formation != null) {
                    s = s.replace("{formacode}", formation);
                    params += s;
                }
            }
        }
        System.out.println(params);
        return params;
    }
}
