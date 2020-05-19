package projeto.covid.controler;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.GrupoEstados;
import projeto.covid.modelo.GrupoMunicipios;
import projeto.covid.modelo.recursos.DiretorioTemp;
import projeto.covid.modelo.recursos.planilha.LeituraPlanilha;
import projeto.covid.modelo.recursos.planilha.Selenium;

public class FXMLLoadingController implements TelaMudanca {

	private Task<Void> tarefa;
	private static Brasil brasil;
	private static GrupoEstados grupoEstados;
	private static GrupoMunicipios grupoMunicipios;

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
//				try {
//					diretorio.extrairParaTemp();
//				} catch (IOException | URISyntaxException e) {
//					e.printStackTrace();
//					System.out.println("Erro ao extrair para diretorio temporario");
//				}
//				Selenium selenium = new Selenium(diretorio);
//				selenium.downloadDados();
//				System.out.println(selenium.getDownloadName());

				System.err.println("Carregando banco de dados");

				brasil = new Brasil();
				grupoEstados = new GrupoEstados();
				grupoMunicipios = new GrupoMunicipios();

				LeituraPlanilha dadoPlanilha = new LeituraPlanilha(diretorio, "HIST_PAINEL_COVIDBR_20200518.xlsx");
				try {
					dadoPlanilha.lerDados(brasil, grupoEstados, grupoMunicipios);
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void succeeded() {
				System.err.println("Banco de dados carregado com sucesso");
				Principal.trocarTela(Telas.PRINCIPAL, "LOAD");
			}

			@Override
			protected void failed() {
				System.err.println("Falha ao carregar banco de dados");
				Principal.trocarTela(Telas.PRINCIPAL, "LOAD");
			}
		};
		return tarefa;
	}

	public static Brasil getBrasil() {
		return FXMLLoadingController.brasil;
	}

	public static GrupoEstados getGrupoEstados() {
		return FXMLLoadingController.grupoEstados;
	}

	public static GrupoMunicipios getGrupoMunicipios() {
		return FXMLLoadingController.grupoMunicipios;
	}
}
