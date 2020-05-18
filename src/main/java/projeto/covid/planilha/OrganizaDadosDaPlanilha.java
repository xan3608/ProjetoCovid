package projeto.covid.planilha;

import java.util.List;

import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.GrupoBrasil;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Municipio;

public class OrganizaDadosDaPlanilha {

	public static void organizarDados(List<DadosLinha> dados, GrupoBrasil grupoBrasil, GrupoEstado grupoEstado,
			GrupoMunicipio grupoMunicipio) {
		
		for (DadosLinha linha : dados) {
			if (linha.getRegiao().equals("Brasil") && linha.getEstado() == null) {
				grupoBrasil.setGrupoBrasil(popularBrasil(linha, grupoBrasil));
			} else if (linha.getMunicipio() == null) {
				grupoEstado.setGrupoEstado(popularEstado(linha, grupoEstado));
			} else {
				grupoMunicipio.setGrupoMunicipios(popularMunicipio(linha, grupoMunicipio));
			}
		}
	}

	private static Municipio popularMunicipio(DadosLinha planilha, GrupoMunicipio grupoMunicipio) {
		Municipio municipio = new Municipio();
		municipio.setData(planilha.getData());
		municipio.setPopulacao(planilha.getPopulacao());
		municipio.setCasosAcumulados(planilha.getCasosAcumulados());
		municipio.setObitosAcumulados(planilha.getObitosAcumulados());
		municipio.setSemanaEpidemia(planilha.getSemanaEpidemia());
		municipio.setRegiao(planilha.getRegiao());
		municipio.setEstado(planilha.getEstado());
		municipio.setMunicipio(planilha.getMunicipio());
		municipio.setRegiaoSaude(planilha.getRegiaoSaude());
		return municipio;
	}

	private static Estado popularEstado(DadosLinha planilha, GrupoEstado grupoEstado) {
		Estado estado = new Estado();
		estado.setData(planilha.getData());
		estado.setPopulacao(planilha.getPopulacao());
		estado.setCasosAcumulados(planilha.getCasosAcumulados());
		estado.setObitosAcumulados(planilha.getObitosAcumulados());
		estado.setSemanaEpidemia(planilha.getSemanaEpidemia());
		estado.setRegiao(planilha.getRegiao());
		estado.setEstado(planilha.getEstado());
		return estado;
	}

	private static Brasil popularBrasil(DadosLinha planilha, GrupoBrasil grupoBrasil) {
		Brasil brasil = new Brasil();
		brasil.setData(planilha.getData());
		brasil.setPopulacao(planilha.getPopulacao());
		brasil.setCasosAcumulados(planilha.getCasosAcumulados());
		brasil.setObitosAcumulados(planilha.getObitosAcumulados());
		brasil.setSemanaEpidemia(planilha.getSemanaEpidemia());
		return brasil;
	}
}
