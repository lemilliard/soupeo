package fr.epsi.i4.soupeoserver.mapper;

import fr.epsi.i4.soupeoserver.analyzer.decisiontree.PageEnum;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Thomas Kint
 */
public enum Word {
    PORTAIL("portail", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"portail", "portal", "pourtail", "accueil"
	))),
    RECHERCHE_EMPLOI("emploi", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"emploi", "boulot", "job", "travail", "travaille"
	))), 
    RECHERCHE_FORMATION("formation", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"formation", "former"
	))),
    MON_COMPTE("compte", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"compte", "compte personnel", "profil"
	))), 
    CV("cv", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"cv", "curriculum vitae", "curriculum"
	))),
    ASSISTANT("assistant", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"assistant", "conseiller", "conseillé"
	))),

	IMPRIMER("imprimer", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"imprimer", "scanner", "copier", "photocopier", "copie", "imprime", "photocopie", "scan"
	))),
	CREER("creer", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"creer", "créé", "faire", "ajouter"
	))),
	REDIRECTION("redirection", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"accéder", "aller", "rendre", "ouvrir", "consulter", "ouvre", "accède", "retourner", "chercher", "rechercher", "recherche", "cherche"
	))),
	CONTACTER("contacter", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"contacter", "appeler", "appelle", "contacte"
	))),

	INTERROGATIF("interrogatif", WordType.INTERROGATION, new ArrayList<>(Arrays.asList(
			"comment", "quoi", "qu'est"
	))),
	DEVELOPPEUR("developpeur", WordType.EMPLOI, new ArrayList<>(Arrays.asList(
			"dev", "developpeur", "developpement"
	))),
	WEBMASTER("webmaster", WordType.EMPLOI, new ArrayList<>(Arrays.asList(
			"web", "master", "webmaster"
	))),
	CHEF_PROJET("chefProjet", WordType.EMPLOI, new ArrayList<>(Arrays.asList(
			"chef", "projet"
	))),
	MONTPELLIER("34172", WordType.VILLE, new ArrayList<>(Arrays.asList(
			"montpellier"
	))),
	PARIS("75", WordType.VILLE, new ArrayList<>(Arrays.asList(
			"paris"
	))),
	MACONNERIE("22334", WordType.FORMATION, new ArrayList<>(Arrays.asList(
			"maçon", "maçonnerie"
	))),
	JAVA("30802", WordType.FORMATION, new ArrayList<>(Arrays.asList(
			"java"
	)));

	private String word;
	private WordType type;
	private ArrayList<String> dictionary;

	Word(String name, WordType type, ArrayList<String> dictionary) {
		this.word = name;
		this.type = type;
		this.dictionary = dictionary;
	}

	public static Word getWordByWordClef(String wordClef, WordType type) {
		for (Word value : values()) {
			if (value.getType().equals(type)) {
				for (String string : value.dictionary) {
					if (string.equals(Normalizer.normalize(wordClef.toLowerCase(), Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "")) || string.equals(wordClef.toLowerCase())) {
						return value;
					}
				}
			}
		}
		return null;
	}

	public String getWord() {
		return word;
	}

	public WordType getType() {
		return type;
	}

	public ArrayList<String> getDictionary() {
		return dictionary;
	}

}
