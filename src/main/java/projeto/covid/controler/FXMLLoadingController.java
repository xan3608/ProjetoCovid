package projeto.covid.controler;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Nacao;
import projeto.covid.modelo.database.planilha.LeituraPlanilha;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class FXMLLoadingController implements TelaMudanca {

	private Task<Void> tarefa;
	private static Nacao nacao;

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

				DiretorioTemp diretorio = new DiretorioTemp();
				consoleLoading.appendText("Extraindo arquivos\n");
//				try {
//					diretorio.extrairParaTemp();
//					consoleLoading.appendText("Arquivos extraidos com sucesso\n");
//				} catch (IOException | URISyntaxException e) {
//					consoleLoading.appendText("Erro ao extrair arquivos\n");
//					e.printStackTrace();
//				}
				
//				consoleLoading.appendText("Iniciando selenium\n");
//				Selenium selenium = new Selenium(diretorio);
//				consoleLoading.appendText("Buscando dados do Ministerio da Saude\n");
//				selenium.downloadDados();
//				System.out.println(selenium.getDownloadName());
				consoleLoading.appendText("Dados obtidos com sucesso\n");
				
				consoleLoading.appendText("Carregando banco de dados\n");
				nacao = new Nacao("Brasil");
				consoleLoading.appendText("Lendo dados da planilha...\n");
				//LeituraPlanilha dadoPlanilha = new LeituraPlanilha(diretorio, selenium.getDownloadName());
				LeituraPlanilha dadoPlanilha = new LeituraPlanilha(diretorio, "HIST_PAINEL_COVIDBR_20200516.xlsx");
				try {
					dadoPlanilha.lerDados(nacao);
					Runtime.getRuntime().gc();
					consoleLoading.appendText("Dados lidos com sucesso\n");
					consoleLoading.appendText("Banco de dados carregado com sucesso\n");
				} catch (IOException e) {
					consoleLoading.appendText("Erro ao ler dados\n");
					e.printStackTrace();
					consoleLoading.appendText("Falha ao carregar banco de dados\n");
				}
				
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
}
