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
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Municipio;

public class FXMLEstatisticaMunicipiosController implements TelaMudanca {

	@FXML
	private ListView<Municipio> lvMunicipios;
	@FXML
	private TextField txFiltroMunicipio;

	private GrupoMunicipio grupoMunicipios;

	@FXML
	private void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	private void botaoSelecionar(ActionEvent event) {

		if (lvMunicipios.getSelectionModel().getSelectedItem() == null) {
			Alert alertaSelecao = new Alert(AlertType.WARNING);
			alertaSelecao.setTitle("Alerta - Estatisticas nos Municipios");
			alertaSelecao.setHeaderText("Erro ao abrir estatistica");
			alertaSelecao.setContentText("Selecione um municipio");
			alertaSelecao.showAndWait();

		} else {
			System.out.println(lvMunicipios.getSelectionModel().getSelectedItem());
			// futura tela
			// Principal.trocarTela(null, lvMunicipios.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	private void filtroMunicipios(KeyEvent event) {

		if (event.getCode() == KeyCode.BACK_SPACE && txFiltroMunicipio.getLength() != 0) {
			txFiltroMunicipio.setText(txFiltroMunicipio.getText(0, (txFiltroMunicipio.getLength() - 1)));
		} else {
			txFiltroMunicipio.setText(txFiltroMunicipio.getText() + event.getText());
		}

		if (txFiltroMunicipio.getText().isEmpty()) {
			carregarMunicipios(grupoMunicipios.getGrupoMunicipios());
			return;
		}

		carregarMunicipios(grupoMunicipios.buscarVariosMunicipios(txFiltroMunicipio.getText()));
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_MUNICIPIOS)) {
			this.grupoMunicipios = (GrupoMunicipio) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + grupoMunicipios);
			carregarMunicipios(grupoMunicipios.getGrupoMunicipios());
		}

	}

	private void carregarMunicipios(List<Municipio> listaMunicipios) {
		lvMunicipios.getItems().clear();
		for (Municipio municipio : listaMunicipios) {
			lvMunicipios.getItems().add(municipio);
		}
	}

}
