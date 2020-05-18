package projeto.covid.tarefa;

import java.io.IOException;
import java.net.URISyntaxException;

import projeto.covid.planilha.Selenium;
import projeto.covid.recursos.DiretorioTemp;

public class Tarefa implements Runnable {

	@Override
	public void run() {
		
		DiretorioTemp diretorio = new DiretorioTemp();
		try {
			diretorio.extrairParaTemp();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Erro ao extrair para diretorio temporario");
		}
		
		Selenium selenium = new Selenium(diretorio);
		selenium.downloadDados();
	}
	
}
