package projeto.covid.modelo;

import java.util.ArrayList;
import java.util.List;

public class GrupoMunicipio {
	private List<Municipio> grupoMunicipios;
	
	public GrupoMunicipio() {
		this.grupoMunicipios = new ArrayList<Municipio>();
	}

	public List<Municipio> getGrupoMunicipios() {
		return grupoMunicipios;
	}

	public void setGrupoMunicipios(Municipio municipio) {
		this.grupoMunicipios.add(municipio);
	}
}
