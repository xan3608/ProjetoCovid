package projeto.covid.controler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import projeto.covid.modelo.GrupoBrasil;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.planilha.DadosPlanilha;
import projeto.covid.planilha.OrganizaDadosDaPlanilha;
import projeto.covid.recursos.DiretorioTemp;

public class FXMLPrincipalController implements TelaMudanca {

	private GrupoBrasil gb;
	private GrupoEstado ge;
	private GrupoMunicipio gm;

	@FXML
	protected void botaoPais(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_PAIS, gb);
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

		gb = new GrupoBrasil();
		ge = new GrupoEstado();
		gm = new GrupoMunicipio();

		DadosPlanilha dadoPlanilha = new DadosPlanilha(diretorio, "HIST_PAINEL_COVIDBR_20200517.xlsx", gb, ge, gm);
		try {
			dadoPlanilha.lerDados();
			OrganizaDadosDaPlanilha.organizarDados(dadoPlanilha.getDadosDasLinhas(), gb, ge, gm);
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
