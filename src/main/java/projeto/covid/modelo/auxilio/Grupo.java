package projeto.covid.modelo.auxilio;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public abstract class Grupo {
	private List<Nacao> grupo;

	protected Grupo() {
		this.grupo = new ArrayList<Nacao>();
	}

	public List<Nacao> getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Nacao e) {
		this.grupo.add(e);
	}

	public void setGrupo(List<Nacao> grupo) {
		this.grupo = grupo;
	}

	public List<Nacao> filtrarGrupoPorNome(String nome) {
		ArrayList<Nacao> elementosEncontrados = new ArrayList<Nacao>();
		for (Nacao e : this.grupo) {
			if (containsIgnoreAccents(e.getNome(), nome.trim().toUpperCase())) {
				elementosEncontrados.add(e);
			}
		}
		return elementosEncontrados;
	}

	public boolean containsIgnoreAccents(String a, String b) {
		String input1 = Normalizer.normalize(a, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();

		String input2 = Normalizer.normalize(b, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();

		return input1.contains(input2);
	}
}
