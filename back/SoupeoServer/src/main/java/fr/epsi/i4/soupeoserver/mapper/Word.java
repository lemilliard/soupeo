package fr.epsi.i4.soupeoserver.mapper;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Thomas Kint
 */
public enum Word {
	LUMIERE("lumiere", WordType.SERVICE, new ArrayList<>(Arrays.asList(
			"lumieres", "lampes", "eclairages"
	))),
	VOLET("volet", WordType.SERVICE, new ArrayList<>(Arrays.asList(
			"volets", "volley"
	))),
	CHAUFFAGE("chauffage", WordType.SERVICE, new ArrayList<>(Arrays.asList(
			"chauffages", "radiateurs"
	))),
	PREPARE("prepare", WordType.SERVICE, new ArrayList<>(Arrays.asList(
			"prepare", "journee", "nuit", "absent", "vacance"
	))),
	MODE("mode", WordType.SERVICE, new ArrayList<>(Arrays.asList(
			"mode", "disco", "relax"
	))),

	ALLUME("allume", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"allumer", "demarrer"
	))),
	ETEINT("eteint", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"eteint", "eteindre"
	))),
	DESCEND("descend", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"descendre", "fermer", "baisser"
	))),
	MONTE("monte", WordType.ACTION, new ArrayList<>(Arrays.asList(
			"monter", "ouvre", "elever"
	))),

	SALON("A", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"salon", "manger"
	))),
	TOILETTES("B", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"toilettes"
	))),
	CHAUFFEEAU("C", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"chauffe-eau"
	))),
	CUISINE("D", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"cuisine", "manger", "nourriture"
	))),
	HALL("E", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"entree", "hall", "rez de chaussee", "vestibule"
	))),
	GARAGE("F", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"garage", "voiture", "atelier", "etabli"
	))),
	COULOIR("G", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"couloir"
	))),
	CHAMBREENFANT("H", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"enfant", "chambre enfant", "lits superposes"
	))),
	SALLEDEAU("I", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"salle d'eau", "douche"
	))),
	CHAMBRESIMPLE("J", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"chambre simple", "lit simple", "guitare"
	))),
	SALLEDEBAIN("K", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"salle de bain", "baignoire"
	))),
	CHAMBREPARENT("L", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"chambre parental", "chambre parents", "parents", "double", "lits doubles"
	))),
	BUANDERIE("M", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"lave-linge", "lave", "linge", "laver"
	))),
	ETAGE("N", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"etage", "bureau", "premier", "bibliothèque"
	))),
	JARDIN("O", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"jardin"
	))),
	PISCINE("P", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"piscine"
	))),
	TERRASSE("Q", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"veranda", "terrasse", "porche"
	))),
	ENTREE("R", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"porte", "porte d'entree"
	))),
	MAISON("S", WordType.PIECE, new ArrayList<>(Arrays.asList(
			"maison", "tout", "toute"
	))),;

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
					if (string.contains(Normalizer.normalize(wordClef.toLowerCase(), Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", ""))) {
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
