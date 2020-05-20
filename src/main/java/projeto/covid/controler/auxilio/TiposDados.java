package projeto.covid.controler.auxilio;

public enum TiposDados {
	CASOS_ACUMULADOS(0), CASOS_NOVOS(1), OBITOS_ACUMULADOS(2), OBITOS_NOVOS(3);

	private int valor;

	TiposDados(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return this.valor;
	}

}
