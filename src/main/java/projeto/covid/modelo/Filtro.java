package projeto.covid.modelo;

import java.text.Normalizer;

public class Filtro {

	public static boolean contanisIgnoreAccents(String a, String b) {
		String input1 = Normalizer.normalize(a, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();

		String input2 = Normalizer.normalize(b, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();

		return input1.contains(input2);
	}
}
