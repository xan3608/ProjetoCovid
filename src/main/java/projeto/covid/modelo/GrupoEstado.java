package projeto.covid.modelo;

import java.util.ArrayList;
import java.util.List;

public class GrupoEstado {
	private List<Estado> grupoEstado;

	public GrupoEstado() {
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

	public ArrayList<Estado> buscarVariosEstados(String nomeEstado) {
		ArrayList<Estado> estadosEncontrados = new ArrayList<Estado>();

		for (Estado estado : this.grupoEstado) {
			if (estado.getEstado().contains(nomeEstado.trim().toUpperCase())) {
				estadosEncontrados.add(estado);
			}
		}
		return estadosEncontrados;
	}

	@Override
	public String toString() {
		return "Estados cadastrados: " + this.grupoEstado.size();
	}
}
