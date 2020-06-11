package projeto.covid.controler;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Dados;
import projeto.covid.modelo.Nacao;

public class FXMLEstatisticaPaisController implements TelaMudanca {

	@FXML
    private TableView<Dados> tvBrasil;
	@FXML
    private TableColumn<Dados, String> colData;
    @FXML
    private TableColumn<Dados, Integer> colPopulacao;
    @FXML
    private TableColumn<Dados, Integer> colSemana;
    @FXML
    private TableColumn<Dados, Integer> colCasos;
    @FXML
    private TableColumn<Dados, Integer> colObito;
	
	private Nacao nacao;

	@FXML
	protected void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	protected void botaoGrafico(ActionEvent event) {
		Principal.trocarTela(Telas.GRAFICOS, nacao);
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
		
		this.colData.setCellValueFactory(new PropertyValueFactory<>("data"));
		this.colPopulacao.setCellValueFactory(new PropertyValueFactory<>("populacao"));
		this.colSemana.setCellValueFactory(new PropertyValueFactory<>("semanaEpidemia"));
		this.colCasos.setCellValueFactory(new PropertyValueFactory<>("casosAcumulados"));
		this.colObito.setCellValueFactory(new PropertyValueFactory<>("obitosAcumulados"));
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_PAIS)) {
			this.nacao = (Nacao) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + this.nacao);
			povoarTabela();
		}
	}
	
	private void povoarTabela() {
		//Othis.tvBrasil.setItems(FXCollections.observableArrayList(this.nacao.getDados()));
	}

}
