package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.GrupoEstados;
import projeto.covid.modelo.GrupoMunicipios;

public class FXMLPrincipalController implements TelaMudanca {

	private Brasil brasil;
	private GrupoEstados ge;
	private GrupoMunicipios gm;

	@FXML
	protected void botaoPais(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_PAIS, brasil);
	}

	@FXML
	protected void botaoEstado(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_ESTADO, ge);
	}

	@FXML
	protected void botaoMunicipio(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_MUNICIPIOS, gm);
	}

	@FXML
	protected void botaoSair(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.PRINCIPAL)) {
			if (dados != null && dados.toString().contains("LOAD")) {
				brasil = FXMLLoadingController.getBrasil();
				ge = FXMLLoadingController.getGrupoEstados();
				gm = FXMLLoadingController.getGrupoMunicipios();
			}
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
		}
	}

}
