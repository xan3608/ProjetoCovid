package projeto.covid.modelo.auxilio;

public class Ponte {

	public void esperar() {
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void notificar() {
		synchronized (this) {
			this.notify();
		}
	}
}
