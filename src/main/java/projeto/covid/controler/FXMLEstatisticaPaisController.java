package projeto.covid.controler;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.Dados;

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
	
	private Brasil brasil;

	@FXML
	protected void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	protected void botaoGrafico(ActionEvent event) {
		
	}

	@FXML
	public void initialize() {
		Principal.addTelaMudanca(this);
		
		colData.setCellValueFactory(new PropertyValueFactory<>("data"));
		colPopulacao.setCellValueFactory(new PropertyValueFactory<>("populacao"));
		colSemana.setCellValueFactory(new PropertyValueFactory<>("semanaEpidemia"));
		colCasos.setCellValueFactory(new PropertyValueFactory<>("casosAcumulados"));
		colObito.setCellValueFactory(new PropertyValueFactory<>("obitosAcumulados"));
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.ESTATISTICA_PAIS)) {
			brasil = (Brasil) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + brasil);
			povoarTabela();
		}
	}
	
	private void povoarTabela() {
		tvBrasil.setItems(FXCollections.observableArrayList(brasil.getGrupoDados()));
	}

}
