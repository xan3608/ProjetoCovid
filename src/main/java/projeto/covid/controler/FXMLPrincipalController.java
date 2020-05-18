package projeto.covid.controler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.GrupoEstados;
import projeto.covid.modelo.GrupoMunicipios;
import projeto.covid.planilha.LeituraPlanilha;
import projeto.covid.recursos.DiretorioTemp;

public class FXMLPrincipalController implements TelaMudanca {

	private Brasil brasil;
	private GrupoEstados ge;
	private GrupoMunicipios gm;

	@FXML
	protected void botaoPais(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_PAIS, brasil);
	}

	@FXML
	protected void botaoEstado(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_ESTADO, ge);
	}

	@FXML
	protected void botaoMunicipio(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_MUNICIPIOS, gm);
	}

	@FXML
	protected void botaoSair(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	public void initialize() {
		Alert alertaSelecao = new Alert(AlertType.WARNING);
		alertaSelecao.show();
		
		DiretorioTemp diretorio = new DiretorioTemp();
//		try {
//			diretorio.extrairParaTemp();
//		} catch (IOException | URISyntaxException e) {
//			e.printStackTrace();
//			System.out.println("Erro ao extrair para diretorio temporario");
//		}

//		Selenium selenium = new Selenium(diretorio);
//		selenium.downloadDados();
//		System.out.println(selenium.getDownloadName());

		brasil = new Brasil();
		ge = new GrupoEstados();
		gm = new GrupoMunicipios();

		LeituraPlanilha dadoPlanilha = new LeituraPlanilha(diretorio, "HIST_PAINEL_COVIDBR_20200517.xlsx");
		try {
			dadoPlanilha.lerDados(brasil, ge, gm);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.PRINCIPAL))
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
	}

}
