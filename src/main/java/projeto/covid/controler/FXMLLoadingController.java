package projeto.covid.controler;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.recursos.planilha.LeituraPlanilha;
import projeto.covid.modelo.recursos.scraping.Selenium;
import projeto.covid.modelo.recursos.temporario.DiretorioTemp;

public class FXMLLoadingController implements TelaMudanca {

	private Task<Void> tarefa;
	private static Brasil brasil;
	private static GrupoEstado grupoEstados;
	private static GrupoMunicipio grupoMunicipios;

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
		tarefa = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				DiretorioTemp diretorio = new DiretorioTemp();
				consoleLoading.appendText("Extraindo arquivos\n");
//				try {
//					diretorio.extrairParaTemp();
//				} catch (IOException | URISyntaxException e) {
//					e.printStackTrace();
//					System.out.println("Erro ao extrair para diretorio temporario");
//				}
//				Selenium selenium = new Selenium(diretorio);
				consoleLoading.appendText("Baixando planilha\n");
//				selenium.downloadDados();
//				System.out.println(selenium.getDownloadName());
				consoleLoading.appendText("Planilha baixada\n");
				System.err.println("Carregando banco de dados");

				brasil = new Brasil();
				grupoEstados = new GrupoEstado();
				grupoMunicipios = new GrupoMunicipio();
				consoleLoading.appendText("Lendo dados da planilha...\n");
				LeituraPlanilha dadoPlanilha = new LeituraPlanilha(diretorio, "HIST_PAINEL_COVIDBR_20200517.xlsx");
				try {
					dadoPlanilha.lerDados(brasil, grupoEstados, grupoMunicipios);
					consoleLoading.appendText("Dados lidos com sucesso!\n");
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void succeeded() {
				System.err.println("Banco de dados carregado com sucesso");
				consoleLoading.appendText("Banco de dados carregado com sucesso\n");
				Principal.trocarTela(Telas.PRINCIPAL, "LOAD");
			}

			@Override
			protected void failed() {
				System.err.println("Falha ao carregar banco de dados");
				consoleLoading.appendText("Falha ao carregar banco de dados\n");

				Principal.trocarTela(Telas.PRINCIPAL, "LOAD");
			}
		};
		return tarefa;
	}

	public static Brasil getBrasil() {
		return FXMLLoadingController.brasil;
	}

	public static GrupoEstado getGrupoEstados() {
		return FXMLLoadingController.grupoEstados;
	}

	public static GrupoMunicipio getGrupoMunicipios() {
		return FXMLLoadingController.grupoMunicipios;
	}
}
