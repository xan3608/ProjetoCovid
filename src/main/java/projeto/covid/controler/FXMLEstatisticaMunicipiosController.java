package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class FXMLEstatisticaMunicipiosController implements TelaMudanca {

	@FXML
	private ListView<?> lvMunicipios;

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
		if (novaTela.equals(Telas.ESTATISTICA_MUNICIPIOS))
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
	}

}
