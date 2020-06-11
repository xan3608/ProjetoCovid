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
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.Nacao;

public class FXMLEstatisticaEstadosController implements TelaMudanca {

	@FXML
	private ListView<Estado> lvEstados;
	@FXML
	private TextField txFiltroEstado;
	
	private Nacao nacao;
	private Filtro<Estado> filtro;
	
	@FXML
	private void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
		this.filtro = null;
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
		carregarEstados(filtro.filtrarGrupo(nacao.getEstados(), event, this.txFiltroEstado));
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
		this.filtro = new Filtro<Estado>();

	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_ESTADO)) {
			this.nacao = ((Nacao) dados);
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + this.nacao.getEstados().size());
			carregarEstados(this.nacao.getEstados());
		}
	}

	public void carregarEstados(List<Estado> estados) {
		this.lvEstados.getItems().clear();
		this.lvEstados.setItems(FXCollections.observableArrayList(estados));
	}

}
