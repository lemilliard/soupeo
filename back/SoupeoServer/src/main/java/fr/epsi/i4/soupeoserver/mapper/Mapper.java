package fr.epsi.i4.soupeoserver.mapper;

import java.util.Arrays;
import java.util.List;

/**
 * @author Thomas Kint
 */
public class Mapper {

	public static String extractWordByType(String str, WordType type) {
		int i = 0;
		String word = null;
		List<String> words = getWords(str);
		while (i < words.size() && word == null) {
			Word m = Word.getWordByWordClef(words.get(i), type);
			if (m != null) {
				word = m.getWord();
			}
			i++;
		}
		return word;
	}

	private static List<String> getWords(String str) {
		return Arrays.asList(str.split(" "));
	}
}
