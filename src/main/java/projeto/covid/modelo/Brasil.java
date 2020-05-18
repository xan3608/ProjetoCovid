package projeto.covid.modelo;

public class Brasil {
	private String data;
	private Integer populacao, semanaEpidemia, casosAcumulados, obitosAcumulados;

	
	@Override
	public String toString() {
		return "Data: '" + this.data + "' Populacao: '" + this.populacao + "' SemanaEpidemia: '" + this.semanaEpidemia
				+ "' Casos Acumulados: '" + this.casosAcumulados + "' Obitos Acumulados: '" + this.obitosAcumulados
				+ "'";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	public Integer getSemanaEpidemia() {
		return semanaEpidemia;
	}

	public void setSemanaEpidemia(Integer semanaEpidemia) {
		this.semanaEpidemia = semanaEpidemia;
	}

	public Integer getCasosAcumulados() {
		return casosAcumulados;
	}

	public void setCasosAcumulados(Integer casosAcumulados) {
		this.casosAcumulados = casosAcumulados;
	}

	public Integer getObitosAcumulados() {
		return obitosAcumulados;
	}

	public void setObitosAcumulados(Integer obitosAcumulados) {
		this.obitosAcumulados = obitosAcumulados;
	}

}
