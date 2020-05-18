package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import projeto.covid.modelo.GrupoBrasil;

public class FXMLEstatisticaPaisController implements TelaMudanca {

	private GrupoBrasil grupoBrasil;

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
//			Principal.trocarTela(null, obj);
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_PAIS)) {
			grupoBrasil = (GrupoBrasil) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + grupoBrasil);
		}
	}

}
