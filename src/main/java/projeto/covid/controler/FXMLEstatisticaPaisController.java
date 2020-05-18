package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FXMLEstatisticaPaisController implements TelaMudanca {

	@FXML
	protected void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_PAIS))
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
	}

}
