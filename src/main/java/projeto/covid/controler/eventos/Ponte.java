package projeto.covid.controler.eventos;

public class Ponte {

	private static String palavraFinal, palavraChave;
	private static ComponentesGUI componentesGUI;
	private static boolean podeExibir = false;
	private static boolean continuar = false;

	public static String getPalavraFinal() {
		return Ponte.palavraFinal;
	}

	public static void setPalavraFinal(String palavraFinal) {
		Ponte.palavraFinal = palavraFinal;
	}

	public static String getPalavraChave() {
		return Ponte.palavraChave;
	}

	public static void setPalavraChave(String palavraChave) {
		Ponte.palavraChave = palavraChave;
	}

	public static ComponentesGUI getComponentesGUI() {
		return Ponte.componentesGUI;
	}

	public static void setComponentesGUI(ComponentesGUI componentesGUI) {
		Ponte.componentesGUI = componentesGUI;
	}

	public static boolean isPodeExibir() {
		return Ponte.podeExibir;
	}

	public static void setPodeExibir(boolean podeExibir) {
		Ponte.podeExibir = podeExibir;
	}

	public static boolean isContinuar() {
		return Ponte.continuar;
	}

	public static void setContinuar(boolean continuar) {
		Ponte.continuar = continuar;
	}

	public static void esperar() {
		synchronized (componentesGUI) {
			while (!Ponte.isContinuar()) {
				try {
					componentesGUI.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static synchronized void notificar() {
		synchronized (componentesGUI) {
				componentesGUI.notify();
				Ponte.continuar = true;
		}
	}
}
