package modelo;

public class Estado {

	private String nome;
	private Integer qtdeConfirmados, qtdeRecuperados, qtdeMortos;
	
	public Estado(String nome, String qtdeConfirmados, String qtdeMortos) {
		this.nome = nome;
		this.qtdeConfirmados = Integer.parseInt(qtdeConfirmados);
		this.qtdeMortos = Integer.parseInt(qtdeMortos);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtdeConfirmados() {
		return qtdeConfirmados;
	}

	public void setQtdeConfirmados(Integer qtdeConfirmados) {
		this.qtdeConfirmados = qtdeConfirmados;
	}

	public Integer getQtdeRecuperados() {
		return qtdeRecuperados;
	}

	public void setQtdeRecuperados(Integer qtdeRecuperados) {
		this.qtdeRecuperados = qtdeRecuperados;
	}

	public Integer getQtdeMortos() {
		return qtdeMortos;
	}

	public void setQtdeMortos(Integer qtdeMortos) {
		this.qtdeMortos = qtdeMortos;
	}
}
