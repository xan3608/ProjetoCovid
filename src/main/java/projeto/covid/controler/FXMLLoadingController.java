package projeto.covid.controler;

import java.io.IOException;
import java.util.Collections;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.Municipio;
import projeto.covid.modelo.Nacao;
import projeto.covid.modelo.database.scraping.ApiCsv;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class FXMLLoadingController implements TelaMudanca {

	private Task<Void> tarefa;
	private static Nacao nacao;
	private static DiretorioTemp diretorio;

	@FXML
	private TextArea consoleLoading;

	@FXML
	public void initialize() throws IOException {
		Thread confInicial = new Thread(criarTarefa());
		confInicial.setDaemon(true);
		confInicial.start();
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {}

	private Task<Void> criarTarefa() {
		this.tarefa = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				diretorio = new DiretorioTemp();
				consoleLoading.appendText("Criando diretorios\n");
				try {
					diretorio.extrairParaTemp();
					consoleLoading.appendText("Diretorio criado com sucesso\n");
				} catch (IOException e) {
					consoleLoading.appendText("Erro ao criar diretorio\n");
					e.printStackTrace();
				}
				diretorio.getDiretorioFiles();
				
				consoleLoading.appendText("Criando objetos\n");
				nacao = new Nacao("Brasil");
				
				ApiCsv api = new ApiCsv();
				api.resquestDados(consoleLoading, nacao);
				
				for (Estado estado : nacao.getEstados()) {
					Collections.sort(estado.getDados());
				}
				
				for (Municipio municipio : nacao.getMunicipios()) {
					Collections.sort(municipio.getDados());
				}
				
				Thread.sleep(1500);
				Runtime.getRuntime().gc();
				return null;
			}

			@Override
			protected void succeeded() {
				Principal.trocarTela(Telas.PRINCIPAL, "LOAD");
			}

			@Override
			protected void failed() {
				Principal.trocarTela(Telas.PRINCIPAL, "LOAD");
			}
		};
		return this.tarefa;
	}

	public static Nacao getNacao() {
		return FXMLLoadingController.nacao;
	}
	
	public static DiretorioTemp getDiretorio() {
		return FXMLLoadingController.diretorio;
	}
}