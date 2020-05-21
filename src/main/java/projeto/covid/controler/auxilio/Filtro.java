package projeto.covid.controler.auxilio;

import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import projeto.covid.modelo.auxilio.Grupo;
import projeto.covid.modelo.auxilio.Nacao;

public class Filtro {
	public static List<Nacao> filtrarGrupo(Grupo grupo, KeyEvent Keyevent, TextField campo) {
		if (Keyevent.getCode() == KeyCode.BACK_SPACE && campo.getLength() != 0) {
			campo.setText(campo.getText(0, (campo.getLength() - 1)));
		} else {
			campo.setText(campo.getText() + Keyevent.getText());
		}

		if (campo.getText().isEmpty()) {
			return grupo.getGrupo();
		}
		return grupo.filtrarGrupoPorNome(campo.getText());
	}
}
