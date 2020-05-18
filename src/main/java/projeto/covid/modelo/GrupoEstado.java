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
}
