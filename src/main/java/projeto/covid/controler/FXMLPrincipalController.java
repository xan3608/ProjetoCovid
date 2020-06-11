package projeto.covid.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Pais;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class FXMLPrincipalController implements TelaMudanca {

	private Pais brasil;
	private GrupoEstado grupoEstados;
	private GrupoMunicipio grupoMunicipios;
	private DiretorioTemp diretorio;

	@FXML
	protected void botaoPais(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_PAIS, this.brasil);
	}

	@FXML
	protected void botaoEstado(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_ESTADO, this.grupoEstados);
	}

	@FXML
	protected void botaoMunicipio(ActionEvent event) {
		Principal.trocarTela(Telas.ESTATISTICA_MUNICIPIOS, this.grupoMunicipios);
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
				this.brasil = FXMLLoadingController.getBrasil();
				this.grupoEstados = FXMLLoadingController.getGrupoEstados();
				this.grupoMunicipios = FXMLLoadingController.getGrupoMunicipios();
				this.diretorio = FXMLLoadingController.getDiretorio();
			}
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + dados);
		}
	}

}
