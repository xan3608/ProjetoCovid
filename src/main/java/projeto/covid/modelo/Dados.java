package projeto.covid.modelo;

import java.io.Serializable;

public class Dados implements Serializable, Comparable<Dados> {

	private static final long serialVersionUID = 1L;

	private String data;
	private Integer populacao, semanaEpidemia, casosAcumulados, obitosAcumulados, casosNovos, obitosNovos;

	public Dados(String data, Integer populacao, Integer semanaEpidemia, Integer casosAcumulados,
			Integer obitosAcumulados, Integer casosNovos, Integer obitosNovos) {
		this.setData(data);
		this.setPopulacao(populacao);
		this.setSemanaEpidemia(semanaEpidemia);
		this.setCasosAcumulados(casosAcumulados);
		this.setObitosAcumulados(obitosAcumulados);
		this.setCasosNovos(casosNovos);
		this.setObitosNovos(obitosNovos);
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

	public Integer getCasosNovos() {
		return casosNovos;
	}

	public void setCasosNovos(Integer casosNovos) {
		this.casosNovos = casosNovos;
	}

	public Integer getObitosNovos() {
		return obitosNovos;
	}

	public void setObitosNovos(Integer obitosNovos) {
		this.obitosNovos = obitosNovos;
	}

	public void adcionaPopulacao(Integer pop) {
		this.populacao += pop;
	}

	public void adcionaCasosAcumulados(Integer casosAcumulados) {
		this.casosAcumulados += casosAcumulados;
	}

	public void adcionaCasosNovos(Integer casosNovos) {
		this.casosNovos += casosNovos;
	}

	public void adcionaObitosAcumulados(Integer obitosAcumulados) {
		this.obitosAcumulados += obitosAcumulados;
	}

	public void adcionaObitosNovos(Integer obitosNovos) {
		this.obitosNovos += obitosNovos;
	}

	@Override
	public int compareTo(Dados dados) {
		return data.compareToIgnoreCase(dados.data);
	}
}
