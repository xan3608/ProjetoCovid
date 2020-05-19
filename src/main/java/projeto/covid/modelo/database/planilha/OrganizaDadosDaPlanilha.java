package projeto.covid.modelo.database.planilha;

import java.util.List;

import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Municipio;

public class OrganizaDadosDaPlanilha {

	public static void organizarDados(List<DadosDaLinha> dados, Brasil brasil, GrupoEstado grupoEstados,
			GrupoMunicipio grupoMunicipios) {

		for (DadosDaLinha linha : dados) {
			if (linha.getRegiao().equalsIgnoreCase("Brasil")) {
				brasil.setGrupoDados(linha.getDados());
			} else if (linha.getMunicipio() == null || linha.getMunicipio().isEmpty()) {
				Estado estado = grupoEstados.buscarEstado(linha.getEstado());
				if (estado != null) {
					estado.setGrupoDados(linha.getDados());
				} else {
					grupoEstados.setGrupoEstado(popularEstado(linha));
				}
			} else {
				Municipio municipio = grupoMunicipios.buscarMunicipio(linha.getMunicipio());
				if (municipio != null) {
					municipio.setGrupoDados(linha.getDados());
				} else {
					grupoMunicipios.setGrupoMunicipios(popularMunicipio(linha));
				}
			}
		}
	}

	private static Municipio popularMunicipio(DadosDaLinha linha) {
		Municipio municipio = new Municipio(linha.getMunicipio(), linha.getRegiaoSaude(), linha.getEstado(),
				linha.getRegiao());
		municipio.setGrupoDados(linha.getDados());
		return municipio;
	}

	private static Estado popularEstado(DadosDaLinha linha) {
		Estado estado = new Estado(linha.getEstado(), linha.getRegiao());
		estado.setGrupoDados(linha.getDados());
		return estado;
	}
}
