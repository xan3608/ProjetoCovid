package projeto.covid.tarefa;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.covid.recursos.DiretorioTemp;
import projeto.covid.scraping.Selenium;

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
