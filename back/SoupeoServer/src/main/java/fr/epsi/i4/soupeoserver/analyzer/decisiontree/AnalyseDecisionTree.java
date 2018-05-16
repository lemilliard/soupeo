/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.soupeoserver.analyzer.decisiontree;

import fr.decisiontree.Config;
import fr.decisiontree.DecisionTree;
import fr.decisiontree.model.Result;

import java.util.HashMap;

import static fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult.ARDUINO;
import static fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult.OK;

/**
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

		config.addDecision(OK.name());
		config.addDecision(ARDUINO.name());

		decisionTree = new DecisionTree(config);
	}

	public DecisionTree getDecisionTree() {
		return decisionTree;
	}

	public void setDecisionTree(DecisionTree decisionTree) {
		this.decisionTree = decisionTree;
	}

	public String analyze(PageEnum pagePrecedente, PageEnum pageActuelle, int nbVisites, int emotionScore) {
		HashMap<String, String> values = new HashMap<>();
		values.put("PagePrecedente", pagePrecedente.name());
		values.put("PageActuelle", pageActuelle.name());
		values.put("NBVisite", String.valueOf(nbVisites));
		values.put("Cam", String.valueOf(CamEnum.getCamEnum(emotionScore).getValue()));

		Result decision;
		decision = decisionTree.decide(values);
		System.out.println(decision.getValue());
		System.out.println(decision.getRatio());

		decisionTree.print();

		return decision.getValue();
	}
}
