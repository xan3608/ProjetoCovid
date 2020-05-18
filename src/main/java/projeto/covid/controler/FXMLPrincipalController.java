package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FXMLPrincipalController implements TelaMudanca {

	@FXML
	private Button botao;

	@FXML
	protected void botaoPais(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_PAIS);
	}

	@FXML
	protected void botaoEstado(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_ESTADO);
	}

	@FXML
	protected void botaoMunicipio(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_MUNICIPIOS);
	}

	@FXML
	protected void botaoSair(ActionEvent event) {
		botao = (Button) event.getSource();
		System.exit(0);
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.PRINCIPAL))
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
	}

}
