package projeto.covid.principal;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import projeto.covid.auxilio.Ponte;
import projeto.covid.eventos.ComponentesGUI;

public class FXMLPrincipalController implements Initializable {

	@FXML
	private Button botao;
	
	private ComponentesGUI gui;

	@FXML
	public void apertarBotao(ActionEvent event) {
		botao = (Button) event.getSource();
		System.out.println(botao.getText());
		gui.setBtnLetra(botao);
		Ponte.notificar();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gui = new ComponentesGUI();
		Ponte.setComponentesGUI(gui);
	}
	
	public void carregarComboBox() {
		
	}
}
