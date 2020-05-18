package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.GrupoEstado;

public class FXMLEstatisticaEstadosController implements TelaMudanca {

	@FXML
	private ListView<Estado> lvEstados;

	private GrupoEstado grupoEstados;

	@FXML
	protected void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	protected void botaoSelecionar(ActionEvent event) {

		if (lvEstados.getSelectionModel().getSelectedItem() == null) {
			Alert alertaSelecao = new Alert(AlertType.WARNING);
			alertaSelecao.setTitle("Alerta - Estatisticas nos Estados");
			alertaSelecao.setHeaderText("Erro ao abrir estatistica");
			alertaSelecao.setContentText("Selecione um estado");
			alertaSelecao.showAndWait();

		} else {
			System.out.println(lvEstados.getSelectionModel().getSelectedItem());
			// futura tela
//			Principal.trocarTela(null, lvEstados.getSelectionModel().getSelectedItem());
		}
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
			carregarEstados();
		}
	}

	public void carregarEstados() {
		grupoEstados.getGrupoEstado().sort((e1, e2) -> e1.getEstado().compareToIgnoreCase(e2.getEstado()));
		lvEstados.getItems().clear();
		for (Estado estado : grupoEstados.getGrupoEstado()) {
			lvEstados.getItems().add(estado);
		}
	}

}
