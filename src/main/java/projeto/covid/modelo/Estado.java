package projeto.covid.modelo;

public class Estado extends Pais implements Comparable<Estado> {
	private String nomeEstado, nomeRegiao;

	public Estado(String estado, String regiao) {
		super();
		this.setNomeEstado(estado);
		this.setNomeRegiao(regiao);
		
		
	}

	public Estado() {
		super();
	}

	@Override
	public String toString() {
		return String.format("%-10s%-20s", this.nomeEstado, this.nomeRegiao);
	}

	@Override
	public int compareTo(Estado e) {
		if (this.nomeRegiao.compareToIgnoreCase(e.getNomeRegiao()) != 0) {
			return 0;
		} else {
			return this.nomeEstado.compareToIgnoreCase(e.getNomeEstado());
		}
	}

	@Override
	public String getNome() {
		return getNomeEstado();
	}

	public String getNomeRegiao() {
		return this.nomeRegiao;
	}

	public void setNomeRegiao(String regiao) {
		this.nomeRegiao = regiao.toUpperCase().trim();
	}

	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public void setNomeEstado(String estado) {
		this.nomeEstado = estado.toUpperCase().trim();
	}

}
