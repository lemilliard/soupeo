/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.soupeoserver.decisionTree;

import fr.decisiontree.*;
import fr.decisiontree.model.Result;
import java.util.HashMap;

/**
 *
 * @author kbouzan
 */
public class AnalyseDecisionTree {

    private static DecisionTree decisionTree;
    private static Config config;

    private static void initDecisionTree() {
        config = new Config("./decisionTree");
        String[] PageParams = new String[]{
            PageEnum.PORTAIL.name(),
            PageEnum.RECHERCHE_EMPLOI.name(),
            PageEnum.RECHERCHE_FORMATION.name(),
            PageEnum.MON_COMPTE.name(),
            PageEnum.CV.name()
        };

        String[] CamParams = new String[]{
            String.valueOf(CamEnum.NORMAL.getValue()),
            String.valueOf(CamEnum.ELEVE.getValue()),
            String.valueOf(CamEnum.TRES_ELEVE.getValue())
        };

        config.addAttribut("PagePrecedente", PageParams);
        config.addAttribut("PageActuelle", PageParams);
        config.addAttribut("NBVisite", "0", "1", "2", "3", "4");
        config.addAttribut("Cam", CamParams);

        config.addDecision("0");
        config.addDecision("1");

        decisionTree = new DecisionTree(config);
//config.addAttribut("PagePrecedente", "portail", "rechercheEmploi", "rechercheFormation", "monCompte", "CV");
//		config.addAttribut("PageSuivante", "portail", "rechercheEmploi", "rechercheFormation", "monCompte", "CV");
//		config.addAttribut("NBVisite", "0", "1", "2", "3", "4", "+5");
//		config.addAttribut("Cam", "0 - 70", "70 - 85", "85 - 100");
//
//		config.addDecision("Indice0");
//		config.addDecision("Indice1");
//
//		decisionTree = new DecisionTree(config);
    }

    public DecisionTree getDecisionTree() {
        return decisionTree;
    }

    public void setDecisionTree(DecisionTree decisionTree) {
        this.decisionTree = decisionTree;
    }

    public static void main(String... args) {
        initDecisionTree();
        HashMap<String, String> values = new HashMap<>();
        values.put("PagePrecedente", PageEnum.PORTAIL.name());
        values.put("PageActuelle", PageEnum.RECHERCHE_EMPLOI.name());
        values.put("NBVisite", "2");
        values.put("Cam", String.valueOf(CamEnum.getCamEnum(76).getValue()));
        Result decision;
        decision = decisionTree.decide(values);
        System.out.println(decision.getValue());
        System.out.println(decision.getRatio());

        values = new HashMap<>();
        values.put("PagePrecedente", PageEnum.RECHERCHE_EMPLOI.name());
        values.put("PageActuelle", PageEnum.RECHERCHE_EMPLOI.name());
        values.put("NBVisite", "2");
        values.put("Cam", String.valueOf(CamEnum.getCamEnum(71).getValue()));
        decision = decisionTree.decide(values);
        System.out.println(decision.getValue());
        System.out.println(decision.getRatio());

        decisionTree.print();
    }

}
