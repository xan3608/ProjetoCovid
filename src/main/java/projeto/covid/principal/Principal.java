package projeto.covid.principal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import modelo.Estado;
import projeto.covid.scraping.Selenium;
import recursos.DiretorioTemp;

public class Principal {

	@SuppressWarnings("unused")
	private static List<Estado> listaEstados;
	@SuppressWarnings("unused")
	private static List<String> listaLinks;

	public static void main(String[] args) throws IOException, URISyntaxException {
		DiretorioTemp diretorioBrowser = new DiretorioTemp();
		diretorioBrowser.extrairBrowser();
		
		Selenium driver = new Selenium();
		driver.downloadDados(diretorioBrowser);
	}

}
