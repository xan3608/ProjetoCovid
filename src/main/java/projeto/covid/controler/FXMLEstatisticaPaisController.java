package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Brasil;

public class FXMLEstatisticaPaisController implements TelaMudanca {

	private Brasil brasil;

	@FXML
	protected void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	protected void botaoSelecionar(ActionEvent event) {

		Alert alertaSelecao = new Alert(AlertType.WARNING);
		alertaSelecao.setTitle("Alerta - Estatisticas no Pais");
		alertaSelecao.setHeaderText("Erro ao abrir estatistica");
		alertaSelecao.setContentText("Selecione uma data");
		alertaSelecao.showAndWait();

		// futura tela
		// Principal.trocarTela(null, obj);
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_PAIS)) {
			brasil = (Brasil) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + brasil);
		}
	}

}
