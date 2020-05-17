package projeto.covid.tarefa;

import projeto.covid.auxilio.Ponte;
import projeto.covid.eventos.ComponentesGUI;

public class Tarefa implements Runnable {

	@Override
	public void run() {
		ComponentesGUI gui = Ponte.getComponentesGUI();

		System.out.println("Fui iniciada");
		while (true) {
			Ponte.esperar();
			System.out.println("Cliquei no botão");
			Ponte.setContinuar(false);
		}
	}

}
