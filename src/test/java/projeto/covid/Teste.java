package projeto.covid;

import java.io.IOException;
import java.net.URISyntaxException;

import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.GrupoBrasil;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.planilha.DadosPlanilha;
import projeto.covid.planilha.Selenium;
import projeto.covid.recursos.DiretorioTemp;

public class Teste {
	public static void main(String[] args) throws IOException, URISyntaxException {
		DiretorioTemp diretorio = new DiretorioTemp();
		diretorio.extrairParaTemp();
		GrupoBrasil br = new GrupoBrasil();
		GrupoEstado e = new GrupoEstado();
		GrupoMunicipio m = new GrupoMunicipio();
		Selenium selenium = new Selenium(diretorio);
		selenium.downloadDados();
		DadosPlanilha dp = new DadosPlanilha(diretorio, selenium.getDownloadName(), br, e, m);
		dp.lerDados();
		for (Brasil b : br.getGrupoBrasil()) {
			System.out.println(b);
		}

	}
}
