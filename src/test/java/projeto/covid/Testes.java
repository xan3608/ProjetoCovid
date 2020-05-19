package projeto.covid;

import java.text.Collator;
import java.util.Locale;

public class Testes {
	public static void main(String[] args) {
//		String a = "Estãdo".toUpperCase();
//		System.out.println(a);
//		String b = "estãdo".toUpperCase();
//		System.out.println(a.contains(b));
		
		Collator collator = Collator.getInstance(new Locale("pt", "BR"));
		collator.setStrength(Collator.PRIMARY);
	}
}
