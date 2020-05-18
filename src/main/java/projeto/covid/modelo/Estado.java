package projeto.covid.modelo;

public class Estado extends Brasil {
	private String estado, regiao;

	public Estado(String estado, String regiao) {
		super();
		this.setEstado(estado);
		this.setRegiao(regiao);
	}

	public Estado() {
		super();
	}

	@Override
	public String toString() {
		// return "Estado: " + this.estado + ", Regiao: " + this.regiao + ", Dados
		// cadastrados: " + super.getGrupoDados().size();
		return String.format("%-20s%-10s", this.regiao, this.estado);

//		return this.regiao + " - " + this.estado;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao.toUpperCase().trim();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado.toUpperCase().trim();
	}

}
