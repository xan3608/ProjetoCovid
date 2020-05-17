package projeto.covid.principal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import projeto.covid.modelo.Estado;
import projeto.covid.recursos.DiretorioTemp;
import projeto.covid.scraping.Selenium;

public class Principal {

	@SuppressWarnings("unused")
	private static List<Estado> listaEstados;
	@SuppressWarnings("unused")
	private static List<String> listaLinks;

	public static void main(String[] args) throws IOException, URISyntaxException {
		DiretorioTemp diretorio = new DiretorioTemp();
		diretorio.extrairParaTemp();
		
		Selenium selenium = new Selenium(diretorio);
		selenium.downloadDados();
	}

}
