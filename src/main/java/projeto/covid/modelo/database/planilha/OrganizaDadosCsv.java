package projeto.covid.modelo.database.planilha;

import projeto.covid.modelo.Dados;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.Municipio;
import projeto.covid.modelo.Nacao;

public class OrganizaDadosCsv {

	public static void organizaDados(String[] dados, Nacao nacao) {
		if (dados[6].equalsIgnoreCase("city") && dados[15].equalsIgnoreCase("False")
				&& !dados[4].equalsIgnoreCase("Importados/Indefinidos")) {
//			System.out.format("%-5s%-15s%-5s%-5s%-40s%-10s%-8s%-8s%-15s%-10s%-10s%-10s%-10s%-15s%-8s%-8s\n", dados[0],
//					dados[1], dados[2], dados[3], dados[4], dados[5], dados[6], dados[7], dados[8], dados[9], dados[10],
//					dados[11], dados[12], dados[13], dados[14], dados[15]);

			Municipio municipio = nacao.buscarMunicipioPorNome(dados[4]);
			if (municipio == null) {
				nacao.setMunicipios(popularMunicipio(dados));
			} else {
				municipio.setDados(popularDados(dados));
			}
		} else if (dados[6].equalsIgnoreCase("state") && dados[15].equalsIgnoreCase("false")) {
//			System.err.format("%-5s%-15s%-5s%-5s%-40s%-10s%-8s%-8s%-15s%-10s%-10s%-10s%-10s%-15s%-8s%-8s\n", dados[0],
//					dados[1], dados[2], dados[3], dados[4], dados[5], dados[6], dados[7], dados[8], dados[9], dados[10],
//					dados[11], dados[12], dados[13], dados[14], dados[15]);
			System.out.println(dados[0]);
			Estado estado = nacao.buscarEstadoPorNome(dados[3]);
			if (estado == null) {
				nacao.setEstados(popularEstado(dados));
			} else {
				estado.setDados(popularDados(dados));
			}
		}
	}

	private static Estado popularEstado(String[] dados) {
		return new Estado(dados[3], popularDados(dados));
	}

	private static Municipio popularMunicipio(String[] dados) {
		return new Municipio(dados[4], dados[3], popularDados(dados));
	}

	private static Dados popularDados(String[] dados) {
		return new Dados(dados[1], Integer.valueOf(dados[13]), Integer.valueOf(dados[0]), Integer.valueOf(dados[7]),
				Integer.valueOf(dados[10]), Integer.valueOf(dados[9]), Integer.valueOf(dados[11]));
	}
}
