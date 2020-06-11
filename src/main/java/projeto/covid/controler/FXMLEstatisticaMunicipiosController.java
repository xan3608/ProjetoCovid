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
import projeto.covid.modelo.Municipio;
import projeto.covid.modelo.Nacao;

public class FXMLEstatisticaMunicipiosController implements TelaMudanca {

	@FXML
	private ListView<Municipio> lvMunicipios;
	@FXML
	private TextField txFiltroMunicipio;

	private Nacao nacao;
	private Filtro<Municipio> filtro;

	@FXML
	private void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	private void botaoSelecionar(ActionEvent event) {

		if (this.lvMunicipios.getSelectionModel().getSelectedItem() == null) {
			Alert alertaSelecao = new Alert(AlertType.WARNING);
			alertaSelecao.setTitle("Alerta - Estatisticas nos Municipios");
			alertaSelecao.setHeaderText("Erro ao abrir estatistica");
			alertaSelecao.setContentText("Selecione um municipio");
			alertaSelecao.showAndWait();

		} else {
			System.out.println(this.lvMunicipios.getSelectionModel().getSelectedItem());
			Principal.trocarTela(Telas.GRAFICOS, this.lvMunicipios.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	private void filtroMunicipios(KeyEvent event) {
		carregarMunicipios(filtro.filtrarGrupo(this.nacao.getMunicipios(), event, this.txFiltroMunicipio));
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
		this.filtro = new Filtro<Municipio>();
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_MUNICIPIOS)) {
			this.nacao = (Nacao) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + this.nacao.getMunicipios().size());
			carregarMunicipios(this.nacao.getMunicipios());
		}

	}

	private void carregarMunicipios(List<Municipio> listaMunicipios) {
		this.lvMunicipios.getItems().clear();
		this.lvMunicipios.setItems(FXCollections.observableArrayList(listaMunicipios));
	}

}
