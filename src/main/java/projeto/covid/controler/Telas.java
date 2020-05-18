package projeto.covid.controler;

public enum Telas {
	PRINCIPAL(1), ESTATISTICA_PAIS(2), ESTATISTICA_ESTADO(3), ESTATISTICA_MUNICIPIOS(4);
	
	private int valor;
	private Telas(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
}
