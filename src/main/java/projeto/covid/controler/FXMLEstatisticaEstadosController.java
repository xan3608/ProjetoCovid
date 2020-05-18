package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class FXMLEstatisticaEstadosController implements TelaMudanca {

	@FXML
	private ListView<?> lvEstados;

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
		if (novaTela.equals(Telas.ESTATISTICA_ESTADO))
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
	}

}
