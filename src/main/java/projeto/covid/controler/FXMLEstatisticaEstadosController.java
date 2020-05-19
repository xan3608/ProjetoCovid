package projeto.covid.controler;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.GrupoEstado;

public class FXMLEstatisticaEstadosController implements TelaMudanca {

	@FXML
	private ListView<Estado> lvEstados;
	@FXML
	private TextField txFiltroEstado;

	private GrupoEstado grupoEstados;

	@FXML
	private void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	private void botaoSelecionar(ActionEvent event) {

		if (lvEstados.getSelectionModel().getSelectedItem() == null) {
			Alert alertaSelecao = new Alert(AlertType.WARNING);
			alertaSelecao.setTitle("Alerta - Estatisticas nos Estados");
			alertaSelecao.setHeaderText("Erro ao abrir estatistica");
			alertaSelecao.setContentText("Selecione um estado");
			alertaSelecao.showAndWait();

		} else {
			System.out.println(lvEstados.getSelectionModel().getSelectedItem());
			// futura tela
			// Principal.trocarTela(null, lvEstados.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	private void filtroEstado(KeyEvent event) {

		if (event.getCode() == KeyCode.BACK_SPACE && txFiltroEstado.getLength() != 0) {
			txFiltroEstado.setText(txFiltroEstado.getText(0, (txFiltroEstado.getLength() - 1)));
		} else {
			txFiltroEstado.setText(txFiltroEstado.getText() + event.getText());
		}

		if (txFiltroEstado.getText().isEmpty()) {
			carregarEstados(grupoEstados.getGrupoEstado());
			return;
		}

		carregarEstados(grupoEstados.buscarVariosEstados(txFiltroEstado.getText()));
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_ESTADO)) {
			this.grupoEstados = (GrupoEstado) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + grupoEstados);
			carregarEstados(grupoEstados.getGrupoEstado());
		}
	}

	public void carregarEstados(List<Estado> listaEstados) {
		lvEstados.getItems().clear();
		for (Estado estado : listaEstados) {
			lvEstados.getItems().add(estado);
		}
	}

}
