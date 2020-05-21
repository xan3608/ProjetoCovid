package projeto.covid.modelo;

import projeto.covid.modelo.auxilio.Grupo;
import projeto.covid.modelo.auxilio.Nacao;

public class GrupoEstado extends Grupo {

	public Estado buscarEstado(String nomeEstado) {
		for (int i = super.getGrupo().size()-1; i >= 0; i--) {
			Nacao estado = super.getGrupo(i);
			if (estado.getNome().equals(nomeEstado.trim().toUpperCase())) {
				return (Estado) estado;
			}
		}
		return null;	
	}

	@Override
	public String toString() {
		return "Estados cadastrados: " + super.getGrupo().size();
	}
}
