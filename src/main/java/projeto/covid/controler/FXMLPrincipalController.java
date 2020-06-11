package projeto.covid.controler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Nacao;
import projeto.covid.modelo.database.temporario.DiretorioTemp;
import projeto.covid.modelo.database.temporario.ObjetosTemp;

public class FXMLPrincipalController implements TelaMudanca {

	private DiretorioTemp diretorio;
	private Nacao nacao;
	
	@FXML
	protected void botaoPais(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_PAIS, this.nacao);
	}

	@FXML
	protected void botaoEstado(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_ESTADO, this.nacao);
	}

	@FXML
	protected void botaoMunicipio(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_MUNICIPIOS, this.nacao);
	}

	@FXML
	protected void botaoSair(ActionEvent event) {
		try {
			ObjetosTemp.gravarDados(diretorio.getDiretorioFiles(), nacao);
		} catch (IOException e) {
			System.out.println("Erro ao gravar dados");
		}
		
		System.exit(0);
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.PRINCIPAL)) {
			if (dados != null && dados.toString().contains("LOAD")) {
				this.nacao = FXMLLoadingController.getNacao();
				this.diretorio = FXMLLoadingController.getDiretorio();
			}
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
		}
	}

}
