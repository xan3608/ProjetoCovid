package projeto.covid.modelo;

import java.util.ArrayList;
import java.util.List;

public class GrupoMunicipios {
	private List<Municipio> grupoMunicipios;
	
	public GrupoMunicipios() {
		this.grupoMunicipios = new ArrayList<Municipio>();
	}

	public List<Municipio> getGrupoMunicipios() {
		return grupoMunicipios;
	}

	public void setGrupoMunicipios(Municipio municipio) {
		this.grupoMunicipios.add(municipio);
	}
	
	public Municipio buscarMunicipio(String nomeMunicipio) {
		for(Municipio municipio : this.grupoMunicipios) {
			if(municipio.getMunicipio().equalsIgnoreCase(nomeMunicipio.trim().toUpperCase())) {
				return municipio;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Municipios cadastrados: " + this.grupoMunicipios.size();
	}
}
