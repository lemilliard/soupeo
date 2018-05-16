/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.soupeoserver.analyzer.decisiontree;

import fr.decisiontree.*;

/**
 *
 * @author kbouzan
 */
public class AnalyseDecisionTree {
    
    private DecisionTree decisionTree;

    public void initDecisionTree() {
        Config config = new Config("./decisiontree");
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
    }

    public DecisionTree getDecisionTree() {
        return decisionTree;
    }

    public void setDecisionTree(DecisionTree decisionTree) {
        this.decisionTree = decisionTree;
    }

}
