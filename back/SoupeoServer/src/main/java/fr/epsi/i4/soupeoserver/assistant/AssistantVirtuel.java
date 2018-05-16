/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.soupeoserver.assistant;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.connection;
import fr.epsi.i4.soupeoserver.mapper.Mapper;
import fr.epsi.i4.soupeoserver.mapper.Word;
import fr.epsi.i4.soupeoserver.mapper.WordType;
import fr.epsi.i4.soupeoserver.model.morphia.PopupContent;
import java.util.ArrayList;
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

    public PopupContent resultatAide(String string) {
        Word page = Mapper.extractWordByType(string, WordType.PAGE);
        Word action = Mapper.extractWordByType(string, WordType.ACTION);
        Word interrogatif = Mapper.extractWordByType(string, WordType.INTERROGATION);
        Word ville = Mapper.extractWordByType(string, WordType.VILLE);
        String villeParam = null;
        if (ville != null) {
            villeParam = ville.getWord();
        }
        int idPopup = resultAide(action, page);
        PopupContent popup = null;
        popup = connection.getDatastore().find(PopupContent.class).field("id_popup").equal(idPopup).get();
        if (interrogatif != null) {
            return popup;
        } else {
            popup.setTitle("");
            String params = "";
            String url = popup.getUrl();
            if (idPopup == 0) {
                List<Word> emplois = Mapper.extractWordsByType(string, WordType.EMPLOI);
                String emploisString = "";
                for (int i = 0; i < emplois.size(); i++) {
                    emploisString += emplois.get(i).getWord();
                    if (i + 1 < emplois.size()) {
                        emploisString += ",";
                    }
                }
                params = ajoutParamsRechercheEmploi(popup.getParamUrl(), emploisString, villeParam);
                url += params;
                popup.setUrl(url);
            } else if (idPopup == 1) {
                Word formation = Mapper.extractWordByType(string, WordType.FORMATION);
                String formationParam = null;
                if(formation != null){
                    formationParam = formation.getWord();
                }
                params = ajoutParamsRechercheFormation(popup.getParamUrl(), formationParam, villeParam);
                url += params;
                popup.setUrl(url);
            } 
            return popup;
        }
    }

    public String ajoutParamsRechercheEmploi(String paramsUrl, String emploi, String ville) {
        paramsUrl = paramsUrl.replace("?", "");
        String[] splitParams = paramsUrl.split("&");
        List<String> params = new ArrayList<>();
        String paramUrl = "?";
        List<String> paramsArray = Arrays.asList(splitParams);
        for(int i = 0; i < paramsArray.size(); i++){
            if (paramsArray.get(i).contains("{lieux}")) {
                if (ville != null) {
                    params.add(paramsArray.get(i).replace("{lieux}", ville));
                }
            }
            if (paramsArray.get(i).contains("{motsCles}")) {
                if (emploi != null) {
                    params.add(paramsArray.get(i).replace("{motsCles}", emploi));
                }
            }
        }
        for(int j = 0; j < params.size(); j++){
            paramUrl += params.get(j);
            if(j + 1 < params.size()){
                paramUrl += "&";
            }
        }
        System.out.println(paramUrl);
        return paramUrl;
    }

    public String ajoutParamsRechercheFormation(String paramsUrl, String formation, String ville) {
        paramsUrl = paramsUrl.replace("?", "");
        String[] splitParams = paramsUrl.split("&");
        List<String> params = new ArrayList<>();
        String paramUrl = "?";
        List<String> paramsArray = Arrays.asList(splitParams);
        for(int i = 0; i < paramsArray.size(); i++){
            if (paramsArray.get(i).contains("{lieux}")) {
                if (ville != null) {
                    params.add(paramsArray.get(i).replace("{lieux}", ville));
                }
            }
            if (paramsArray.get(i).contains("{formacode}")) {
                if (formation != null) {
                    params.add(paramsArray.get(i).replace("{formacode}", formation));
                }
            }
        }
        for(int j = 0; j < params.size(); j++){
            paramUrl += params.get(j);
            if(j + 1 < params.size()){
                paramUrl += "&";
            }
        }
        System.out.println(paramUrl);
        return paramUrl;
    }
}
