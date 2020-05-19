package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Municipio;

public class FXMLEstatisticaMunicipiosController implements TelaMudanca {

	@FXML
	private ListView<Municipio> lvMunicipios;

	private GrupoMunicipio grupoMunicipios;

	@FXML
	protected void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	protected void botaoSelecionar(ActionEvent event) {

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
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_MUNICIPIOS)) {
			grupoMunicipios = (GrupoMunicipio) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + grupoMunicipios);
			carregarMunicipios();
		}

	}

	public void carregarMunicipios() {
		grupoMunicipios.getGrupoMunicipios().sort((e1, e2) -> e1.getEstado().compareToIgnoreCase(e2.getEstado()));
		lvMunicipios.getItems().clear();
		for (Municipio municipio : grupoMunicipios.getGrupoMunicipios()) {
			lvMunicipios.getItems().add(municipio);
		}
	}

}
