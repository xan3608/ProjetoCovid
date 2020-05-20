package projeto.covid.controler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Pais;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.database.planilha.LeituraPlanilha;
import projeto.covid.modelo.database.scraping.Selenium;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class FXMLLoadingController implements TelaMudanca {

	private Task<Void> tarefa;
	private static Pais brasil;
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
					consoleLoading.appendText("Arquivos extraidos com sucesso\n");
//				} catch (IOException | URISyntaxException e) {
					consoleLoading.appendText("Erro ao extrair arquivos\n");
//					e.printStackTrace();
//				}
				
				consoleLoading.appendText("Iniciando selenium\n");
				//Selenium selenium = new Selenium(diretorio);
				consoleLoading.appendText("Buscando dados do Ministerio da Saude\n");
				//selenium.downloadDados();
				//System.out.println(selenium.getDownloadName());
				consoleLoading.appendText("Dados obtidos com sucesso\n");
				
				consoleLoading.appendText("Carregando banco de dados\n");
				brasil = new Pais();
				grupoEstados = new GrupoEstado();
				grupoMunicipios = new GrupoMunicipio();
				consoleLoading.appendText("Lendo dados da planilha...\n");
				//LeituraPlanilha dadoPlanilha = new LeituraPlanilha(diretorio, selenium.getDownloadName());
				LeituraPlanilha dadoPlanilha = new LeituraPlanilha(diretorio, "HIST_PAINEL_COVIDBR_19mai2020.xlsx");

				try {
					dadoPlanilha.lerDados(brasil, grupoEstados, grupoMunicipios);
					consoleLoading.appendText("Dados lidos com sucesso\n");
					grupoEstados.getGrupo().sort(null);
					grupoMunicipios.getGrupo().sort(null);
					consoleLoading.appendText("Banco de dados carregado com sucesso\n");
				} catch (IOException e) {
					consoleLoading.appendText("Erro ao ler dados\n");
					e.printStackTrace();
					consoleLoading.appendText("Falha ao carregar banco de dados\n");
				}
				
				Thread.sleep(1500);

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
		return tarefa;
	}

	public static Pais getBrasil() {
		return FXMLLoadingController.brasil;
	}

	public static GrupoEstado getGrupoEstados() {
		return FXMLLoadingController.grupoEstados;
	}

	public static GrupoMunicipio getGrupoMunicipios() {
		return FXMLLoadingController.grupoMunicipios;
	}
}
