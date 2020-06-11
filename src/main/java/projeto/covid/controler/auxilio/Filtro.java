package projeto.covid.controler.auxilio;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Filtro <A>{
	public List<A> filtrarGrupo(List<A> grupo, KeyEvent Keyevent, TextField campo) {
		if (Keyevent.getCode() == KeyCode.BACK_SPACE && campo.getLength() != 0) {
			campo.setText(campo.getText(0, (campo.getLength() - 1)));
		} else {
			campo.setText(campo.getText() + Keyevent.getText());
		}

		if (campo.getText().isEmpty()) {
			return grupo;
		}
		return buscarGrupo(grupo, campo.getText());
	}
	
	public List<A> buscarGrupo(List<A> grupo, String nome) {
		ArrayList<A> elementosEncontrados = new ArrayList<A>();
		for (A e : grupo) {
			if (Filtro.containsIgnoreAccents(e.toString(), nome.trim().toUpperCase())) {
				elementosEncontrados.add(e);
			}
		}
		return elementosEncontrados;
	}
	
	public static boolean containsIgnoreAccents(String a, String b) {
		String input1 = Normalizer.normalize(a, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();

		String input2 = Normalizer.normalize(b, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();

		return input1.contains(input2);
	}
}
