package projeto.covid.modelo;

public class Dados {
	private String data;
	private Integer populacao, semanaEpidemia, casosAcumulados, obitosAcumulados;

	public Dados(String data, Integer populacao, Integer semanaEpidemia, Integer casosAcumulados,
			Integer obitosAcumulados) {
		this.data = data;
		this.populacao = populacao;
		this.semanaEpidemia = semanaEpidemia;
		this.casosAcumulados = casosAcumulados;
		this.obitosAcumulados = obitosAcumulados;
	}

	public Dados() {
		
	}
	@Override
	public String toString() {
		return "Data: '" + this.data + "' Populacao: '" + this.populacao + "' SemanaEpidemia: '" + this.semanaEpidemia
				+ "' CasosAcumulados: '" + this.casosAcumulados + "' Obitos Acumulados: '" + this.obitosAcumulados
				+ "' ";
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getPopulacao() {
		return this.populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	public Integer getSemanaEpidemia() {
		return this.semanaEpidemia;
	}

	public void setSemanaEpidemia(Integer semanaEpidemia) {
		this.semanaEpidemia = semanaEpidemia;
	}

	public Integer getCasosAcumulados() {
		return this.casosAcumulados;
	}

	public void setCasosAcumulados(Integer casosAcumulados) {
		this.casosAcumulados = casosAcumulados;
	}

	public Integer getObitosAcumulados() {
		return this.obitosAcumulados;
	}

	public void setObitosAcumulados(Integer obitosAcumulados) {
		this.obitosAcumulados = obitosAcumulados;
	}

}
