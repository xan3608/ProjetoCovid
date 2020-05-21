package projeto.covid.modelo;

import projeto.covid.modelo.auxilio.Grupo;
import projeto.covid.modelo.auxilio.Nacao;

public class GrupoEstado extends Grupo {

	public Estado buscarEstado(String nomeEstado) {
		for (Nacao estado : super.getGrupo()) {
			if (super.containsIgnoreAccents(estado.getNome(), nomeEstado.trim())) {
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
