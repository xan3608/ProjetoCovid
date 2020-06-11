package projeto.covid.controler;

import java.io.IOException;
import java.util.Collections;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Pais;
import projeto.covid.modelo.auxilio.Nacao;
import projeto.covid.modelo.database.scraping.ApiCsv;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class FXMLLoadingController implements TelaMudanca {

	private Task<Void> tarefa;
	private static Pais brasil;
	private static GrupoEstado grupoEstados;
	private static GrupoMunicipio grupoMunicipios;
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
				brasil = new Pais("Brasil");
				grupoEstados = new GrupoEstado();
				grupoMunicipios = new GrupoMunicipio();
				
				ApiCsv api = new ApiCsv();
				api.resquestDados(consoleLoading, grupoEstados, grupoMunicipios);
				
				for (Nacao estado : grupoEstados.getGrupo()) {
					Collections.sort(estado.getDados());
				}
				
				for (Nacao estado : grupoMunicipios.getGrupo()) {
					Collections.sort(estado.getDados());
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

	public static Pais getBrasil() {
		return FXMLLoadingController.brasil;
	}

	public static GrupoEstado getGrupoEstados() {
		return FXMLLoadingController.grupoEstados;
	}

	public static GrupoMunicipio getGrupoMunicipios() {
		return FXMLLoadingController.grupoMunicipios;
	}
	
	public static DiretorioTemp getDiretorio() {
		return FXMLLoadingController.diretorio;
	}
}
