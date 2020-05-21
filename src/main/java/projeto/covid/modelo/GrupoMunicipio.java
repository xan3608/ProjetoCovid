package projeto.covid.modelo;

import projeto.covid.modelo.auxilio.Grupo;
import projeto.covid.modelo.auxilio.Nacao;

public class GrupoMunicipio extends Grupo{

	public Municipio buscarMunicipio(String nomeMunicipio) {
		for (Nacao municipio : super.getGrupo()) {
			if (super.containsIgnoreAccents(municipio.getNome(), nomeMunicipio.trim())) {
				return (Municipio) municipio;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Municipios cadastrados: " + super.getGrupo().size();
	}
}
