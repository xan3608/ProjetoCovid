package projeto.covid.modelo;

import java.util.ArrayList;
import java.util.List;

public class GrupoEstados {
	private List<Estado> grupoEstado;
	
	public GrupoEstados() {
		this.grupoEstado = new ArrayList<Estado>();
	}

	public List<Estado> getGrupoEstado() {
		return grupoEstado;
	}

	public void setGrupoEstado(Estado estado) {
		this.grupoEstado.add(estado);
	}

	public Estado buscarEstado(String nomeEstado) {
		for (Estado estado : this.grupoEstado) {
			if (estado.getEstado().equalsIgnoreCase(nomeEstado.trim().toUpperCase())) {
				return estado;
			}
		}
		return null;
	}
	 @Override
	public String toString() {
		return "Estados cadastrados: " + this.grupoEstado.size();
	}
}
