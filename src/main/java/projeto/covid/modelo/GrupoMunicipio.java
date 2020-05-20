package projeto.covid.modelo;

public class GrupoMunicipio extends Grupo{

	public Municipio buscarMunicipio(String nomeMunicipio) {
		for (Nacao municipio : super.getGrupo()) {
			if (municipio.getNome().equalsIgnoreCase(nomeMunicipio.trim())) {
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
