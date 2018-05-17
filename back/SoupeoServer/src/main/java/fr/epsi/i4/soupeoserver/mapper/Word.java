package fr.epsi.i4.soupeoserver.mapper;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Thomas Kint
 */
public enum Word {
	PORTAIL("portail", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"portail", "portal", "pourtail", "accueil", "home", "page d'accueil"
	))),
	RECHERCHE_EMPLOI("emploi", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"emploi", "boulot", "job", "travail", "travaille", "lampe"
	))),
	RECHERCHE_FORMATION("formation", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"formation", "former"
	))),
	MON_COMPTE("compte", WordType.PAGE, new ArrayList<>(Arrays.asList(
			"compte", "comptes", "compte personnel", "comptes personnels", "profil"
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
			"acceder", "aller", "rendre", "ouvrir", "consulter", "ouvre", "accede", "retourner", "chercher", "rechercher", "recherche", "cherche",
			"veux", "vouloir", "voudrais", "voudrai", "voulons", "voulez"
	))),
	CONTACTER("contacter", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"contacter", "appeler", "appelle", "contacte"
	))),

	INTERROGATIF("interrogatif", WordType.INTERROGATION, new ArrayList<>(Arrays.asList(
			"comment", "quoi", "qu'est"
	))),
	DEVELOPPEUR("developpeur", WordType.EMPLOI, new ArrayList<>(Arrays.asList(
			"dev", "dave", "developpeur", "developpement"
	))),
	WEBMASTER("webmaster", WordType.EMPLOI, new ArrayList<>(Arrays.asList(
			"web", "master", "webmaster"
	))),
	CHEF_PROJET("chefProjet", WordType.EMPLOI, new ArrayList<>(Arrays.asList(
			"chef", "projet"
	))),
	RESTAURATION("restauration", WordType.EMPLOI, new ArrayList<>(Arrays.asList(
			"restauration", "resto", "serveur", "esclave"
	))),

	MONTPELLIER("34172", WordType.VILLE, new ArrayList<>(Arrays.asList(
			"montpellier"
	))),
	PARIS("75D", WordType.VILLE, new ArrayList<>(Arrays.asList(
			"paris", "paname", "capitale", "capitole", "capital", "capitol"
	))),
	PERPIGNAN("66136", WordType.VILLE, new ArrayList<>(Arrays.asList(
			"perpi", "perpignan"
	))),
	GUINGAMP("22070", WordType.VILLE, new ArrayList<>(Arrays.asList(
			"guingamp"
	))),

	MACONNERIE("22334", WordType.FORMATION, new ArrayList<>(Arrays.asList(
			"maçon", "maçonnerie"
	))),
	JAVA("30802", WordType.FORMATION, new ArrayList<>(Arrays.asList(
			"java", "C#"
	))),
	JARDINAGE("15039", WordType.FORMATION, new ArrayList<>(Arrays.asList(
			"jardinage", "jardinage", "sharp", "c#"
	))),
	RESTO("42746", WordType.FORMATION, new ArrayList<>(Arrays.asList(
			"resto", "restauration"
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
