package projeto.covid.controler;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import projeto.covid.controler.auxilio.Filtro;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.auxilio.Nacao;

public class FXMLEstatisticaEstadosController implements TelaMudanca {

	@FXML
	private ListView<Nacao> lvEstados;
	@FXML
	private TextField txFiltroEstado;

	private GrupoEstado grupoEstados;

	@FXML
	private void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	private void botaoSelecionar(ActionEvent event) {

		if (this.lvEstados.getSelectionModel().getSelectedItem() == null) {
			Alert alertaSelecao = new Alert(AlertType.WARNING);
			alertaSelecao.setTitle("Alerta - Estatisticas nos Estados");
			alertaSelecao.setHeaderText("Erro ao abrir estatistica");
			alertaSelecao.setContentText("Selecione um estado");
			alertaSelecao.showAndWait();

		} else {
			System.out.println(this.lvEstados.getSelectionModel().getSelectedItem());
			Principal.trocarTela(Telas.GRAFICOS, this.lvEstados.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	private void filtroEstado(KeyEvent event) {
		carregarEstados(Filtro.filtrarGrupo(this.grupoEstados, event, this.txFiltroEstado));
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_ESTADO)) {
			this.grupoEstados = (GrupoEstado) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + this.grupoEstados);
			carregarEstados(this.grupoEstados.getGrupo());
		}
	}

	public void carregarEstados(List<Nacao> listaEstados) {
		this.lvEstados.getItems().clear();
		this.lvEstados.setItems(FXCollections.observableArrayList(listaEstados));
	}

}
