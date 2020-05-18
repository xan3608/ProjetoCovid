package projeto.covid.modelo;

public class Estado extends Brasil{
	private String regiao, estado;

	@Override
	public String toString() {
		return this.estado;
	}
	
	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
