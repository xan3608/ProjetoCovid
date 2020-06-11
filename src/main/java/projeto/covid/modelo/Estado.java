package projeto.covid.modelo;

public class Estado extends Pais implements Comparable<Estado> {
	private String nomeEstado;

	public Estado(String estado, Dados dados) {
		super();
		this.setNomeEstado(estado);
		super.setDados(dados);
	}

	public Estado() {
		super();
	}

	@Override
	public String toString() {
		return String.format("%-10s", this.nomeEstado);
	}

	@Override
	public int compareTo(Estado e) {
		return this.nomeEstado.compareToIgnoreCase(e.getNomeEstado());
	}

	@Override
	public String getNome() {
		return getNomeEstado();
	}

	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public void setNomeEstado(String estado) {
		this.nomeEstado = estado.toUpperCase().trim();
	}

}
