package projeto.covid.modelo;

import java.util.ArrayList;
import java.util.List;

public class Brasil {
	private List<Dados> grupoDados;

	public Brasil() {
		this.grupoDados = new ArrayList<Dados>();
	}
	
	@Override
	public String toString() {
		return "Brasil, Dados: " + grupoDados.size();
	}
	public List<Dados> getGrupoDados() {
		return grupoDados;
	}

	public void setGrupoDados(Dados dados) {
		this.grupoDados.add(dados);
	}
	public void setGrupoDados(List<Dados> grupoDados) {
		this.grupoDados = grupoDados;
	}

}
