package projeto.covid.modelo;

import projeto.covid.modelo.auxilio.Grupo;
import projeto.covid.modelo.auxilio.Nacao;

public class GrupoMunicipio extends Grupo {

	public Nacao buscarMunicipio(String nomeMunicipio) {
		if(super.getGrupo().isEmpty()) {
			return null;
		}
		
		if (super.getGrupo().get(super.getGrupo().size() - 1).getNome().equals(nomeMunicipio.trim().toUpperCase()))
			return super.getGrupo().get(super.getGrupo().size() - 1);
//		for (int i = super.getGrupo().size()-1; i >= 0; i--) {
//			Nacao municipio = super.getGrupo(i);
//			if (municipio.getNome().equals(nomeMunicipio.trim().toUpperCase())) {
//				return (Municipio) municipio;
//			}
//		}
		return null;
	}

	@Override
	public String toString() {
		return "Municipios cadastrados: " + super.getGrupo().size();
	}
}
