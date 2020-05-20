package projeto.covid.modelo.database.planilha;

import java.util.List;

import projeto.covid.modelo.Pais;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Municipio;

public class OrganizaDadosDaPlanilha {

	public static void organizarDados(List<DadosDaLinha> dados, Pais brasil, GrupoEstado grupoEstados,
			GrupoMunicipio grupoMunicipios) {

		for (DadosDaLinha linha : dados) {
			if (linha.getRegiao().equalsIgnoreCase("Brasil")) {
				brasil.setDados(linha.getDados());
			} else if ((linha.getMunicipio() == null || linha.getMunicipio().isEmpty()) &&
						linha.getCodMunicipio() == null || linha.getCodMunicipio() <= 0) {
				Estado estado = grupoEstados.buscarEstado(linha.getEstado());
				if (estado != null) {
					estado.setDados(linha.getDados());
				} else {
					grupoEstados.setGrupo(popularEstado(linha));
				}
			} else {
				Municipio municipio = grupoMunicipios.buscarMunicipio(linha.getMunicipio());
				if (municipio != null) {
					municipio.setDados(linha.getDados());
				} else {
					grupoMunicipios.setGrupo(popularMunicipio(linha));
				}
			}
		}
	}

	private static Municipio popularMunicipio(DadosDaLinha linha) {
		Municipio municipio = new Municipio(linha.getMunicipio(), linha.getRegiaoSaude(), linha.getEstado(),
				linha.getRegiao());
		municipio.setDados(linha.getDados());
		return municipio;
	}

	private static Estado popularEstado(DadosDaLinha linha) {
		Estado estado = new Estado(linha.getEstado(), linha.getRegiao());
		estado.setDados(linha.getDados());
		return estado;
	}
}
