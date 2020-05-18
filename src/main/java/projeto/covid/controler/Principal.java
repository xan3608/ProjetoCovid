package projeto.covid.controler;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {

	private static Stage stage;
	
	private static Scene scenePrincipal;
	private static Scene sceneEstatisticaPais;
	private static Scene sceneEstatisticaEstados;
	private static Scene sceneEstatisticaMunicipios;
	
	private static ArrayList<TelaMudanca> ouvintes = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			stage = primaryStage;
			
			stage.setTitle("Projeto Covid-19");
			
			Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("../visualizacao/FXMLPrincipal.fxml"));
			Parent fxmlEstatisticaPais = FXMLLoader.load(getClass().getResource("../visualizacao/FXMLEstatisticaPais.fxml"));
			Parent fxmlEstatisticaEstados = FXMLLoader.load(getClass().getResource("../visualizacao/FXMLEstatisticaEstados.fxml"));
			Parent fxmlEstatisticaMunicipios = FXMLLoader.load(getClass().getResource("../visualizacao/FXMLEstatisticaMunicipios.fxml"));
			
			scenePrincipal = new Scene(fxmlPrincipal);
			sceneEstatisticaPais = new Scene(fxmlEstatisticaPais);
			sceneEstatisticaEstados = new Scene(fxmlEstatisticaEstados);
			sceneEstatisticaMunicipios = new Scene(fxmlEstatisticaMunicipios);
			
			stage.setScene(scenePrincipal);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void trocarTela(Telas tela) {
		trocarTela(tela, null);
	}
	
	public static void trocarTela(Telas tela, Object dados) {
		switch (tela.getValor()) {
		case 1:
			stage.setTitle("Projeto Covid-19");
			stage.setScene(scenePrincipal);
			notificarTodosOuvintes(tela, dados);
			break;
		case 2:
			stage.setTitle("Projeto Covid-19 - Estatisticas no Pais");
			stage.setScene(sceneEstatisticaPais);
			notificarTodosOuvintes(tela, dados);
			break;
		case 3:
			stage.setTitle("Projeto Covid-19 - Estatisticas nos Estados");
			stage.setScene(sceneEstatisticaEstados);
			notificarTodosOuvintes(tela, dados);
			break;
		case 4:
			stage.setTitle("Projeto Covid-19 - Estatisticas nos Municipios");
			stage.setScene(sceneEstatisticaMunicipios);
			notificarTodosOuvintes(tela, dados);
			break;
		}
	}
	
	public static void addTelaMudanca(TelaMudanca novaTela) {
		ouvintes.add(novaTela);
	}
	
	private static void notificarTodosOuvintes(Telas novaTela, Object dados) {
		for (TelaMudanca telaMudanca : ouvintes) {
			telaMudanca.mudouTela(novaTela, dados);
		}
	}
}
