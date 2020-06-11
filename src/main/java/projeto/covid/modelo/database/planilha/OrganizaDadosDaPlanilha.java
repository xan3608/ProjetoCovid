package projeto.covid.modelo.database.planilha;

import projeto.covid.modelo.Estado;
import projeto.covid.modelo.Municipio;
import projeto.covid.modelo.Nacao;

public class OrganizaDadosDaPlanilha {

	public static void organizarDados(DadosDaLinha linha, Nacao nacao) {

		if ((linha.getMunicipio() == null || linha.getMunicipio().trim().isEmpty()) && 
				linha.getCodMunicipio() == null || linha.getCodMunicipio() <= 0) {
			
			Estado estado = nacao.buscarEstadoPorNome(linha.getEstado());
			if (estado != null) {
				estado.setDados(linha.getDados());
			} else {
				nacao.setEstados(popularEstado(linha));
			}
			return;
		}
		if (linha.getMunicipio().trim().isEmpty()) {
			return;
		}

		Municipio municipio = nacao.buscarMunicipioPorNome(linha.getMunicipio());
		if (municipio != null) {
			municipio.setDados(linha.getDados());
		} else {
			nacao.setMunicipios(popularMunicipio(linha));
		}
	}

	private static Municipio popularMunicipio(DadosDaLinha linha) {
		return new Municipio(linha.getMunicipio(), linha.getEstado(), linha.getDados());
	}

	private static Estado popularEstado(DadosDaLinha linha) {
		return new Estado(linha.getEstado(), linha.getDados());
	}
}
