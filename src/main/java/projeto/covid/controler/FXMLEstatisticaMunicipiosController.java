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
import projeto.covid.controler.auxilio.Filtro;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Municipio;
import projeto.covid.modelo.Nacao;

public class FXMLEstatisticaMunicipiosController implements TelaMudanca {

	@FXML
	private ListView<Nacao> lvMunicipios;
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
			// Principal.trocarTela(null,
			// lvMunicipios.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	private void filtroMunicipios(KeyEvent event) {
		carregarMunicipios(Filtro.filtrarGrupo(grupoMunicipios, event, txFiltroMunicipio));
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
			carregarMunicipios(grupoMunicipios.getGrupo());
		}

	}

	private void carregarMunicipios(List<Nacao> listaMunicipios) {
		lvMunicipios.getItems().clear();
		for (Nacao municipio : listaMunicipios) {
			lvMunicipios.getItems().add(municipio);
		}
	}

}
