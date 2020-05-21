package projeto.covid.controler.principal;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;

public class Principal extends Application {

	private static Stage stage;

	private static Scene sceneLoading;
	private static Scene scenePrincipal;
	private static Scene sceneEstatisticaPais;
	private static Scene sceneEstatisticaEstados;
	private static Scene sceneEstatisticaMunicipios;
	private static Scene sceneGraficos;
	private static ArrayList<TelaMudanca> ouvintes = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {

		try {
			stage = primaryStage;

			stage.setTitle("Projeto Covid-19");

			Parent fxmlLoading = FXMLLoader.load(getClass().getResource("../../visualizacao/FXMLLoading.fxml"));
			Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("../../visualizacao/FXMLPrincipal.fxml"));
			Parent fxmlEstatisticaPais = FXMLLoader
					.load(getClass().getResource("../../visualizacao/FXMLEstatisticaPais.fxml"));
			Parent fxmlEstatisticaEstados = FXMLLoader
					.load(getClass().getResource("../../visualizacao/FXMLEstatisticaEstados.fxml"));
			Parent fxmlEstatisticaMunicipios = FXMLLoader
					.load(getClass().getResource("../../visualizacao/FXMLEstatisticaMunicipios.fxml"));
			Parent fxmlGraficos = FXMLLoader
					.load(getClass().getResource("../../visualizacao/FXMLGraficos.fxml"));

			sceneLoading = new Scene(fxmlLoading);
			scenePrincipal = new Scene(fxmlPrincipal);
			sceneEstatisticaPais = new Scene(fxmlEstatisticaPais);
			sceneEstatisticaEstados = new Scene(fxmlEstatisticaEstados);
			sceneEstatisticaMunicipios = new Scene(fxmlEstatisticaMunicipios);
			sceneGraficos = new Scene(fxmlGraficos);
			
			stage.setScene(sceneLoading);
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
		case 5:
			stage.setTitle("Projeto Covid - 19 - Graficos");
			stage.setScene(sceneGraficos);
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
