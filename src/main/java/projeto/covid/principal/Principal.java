package projeto.covid.principal;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.covid.auxilio.Ponte;
import projeto.covid.modelo.Estado;
import projeto.covid.tarefa.Tarefa;

public class Principal extends Application {
	
	@SuppressWarnings("unused")
	private static List<Estado> listaEstados;
	@SuppressWarnings("unused")
	private static List<String> listaLinks;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Projeto Covid");
			primaryStage.show();
			
			Thread codigo = new Thread(new Tarefa(), "Codigo");
			codigo.setDaemon(true);
			codigo.start();
			
//			DiretorioTemp diretorio = new DiretorioTemp();
//			diretorio.extrairParaTemp();
//			
//			Selenium selenium = new Selenium(diretorio);
//			selenium.downloadDados();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void exibicao(Stage primaryStage, boolean exibir) {
		while (!Ponte.isPodeExibir()) {
			primaryStage.close();
		}
		primaryStage.show();
	}
}
