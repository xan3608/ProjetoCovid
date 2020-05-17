package projeto.covid.eventos;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ComponentesGUI {

	private Button btnLetra;
	private TextField textFieldTentativas, TextFieldMensagens, textFieldPalavra;
	private GridPane painelBotoes;

	public ComponentesGUI() {
	}

	public Button getBtnLetra() {
		return btnLetra;
	}

	public void setBtnLetra(Button btnLetra) {
		this.btnLetra = btnLetra;
	}

	public void setTextFieldTentativas(String string) {
		this.textFieldTentativas.setText(string);
	}

	public void setTextFieldMensagens(String string) {
		TextFieldMensagens.setText(string);
	}

	public void setTextFieldPalavra(String palavraChave) {
		this.textFieldPalavra.setText(palavraChave);
	}

	public GridPane getPainelBotoes() {
		return this.painelBotoes;
	}

	public void setPainelBotoes(GridPane painelBotoes) {
		this.painelBotoes = painelBotoes;
	}

	public void verificarBotao() {
		System.out.println(btnLetra.getText());
	}
}
