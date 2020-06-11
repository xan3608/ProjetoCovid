package projeto.covid.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estado implements Comparable<Estado>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeEstado;
	private List<Dados> dados;

	public Estado(String estado, Dados dados) {
		this.dados = new ArrayList<Dados>();
		this.setNomeEstado(estado);
		this.setDados(dados);
	}

	@Override
	public int compareTo(Estado outro) {
		return this.getNome().compareTo(outro.getNome());
	}
	@Override
	public String toString() {
		return this.getNome();
	}

	public String getNome() {
		return this.getNomeEstado();
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado.trim().toUpperCase();
	}

	public List<Dados> getDados() {
		return dados;
	}
	
	public void setDados(List<Dados> dados) {
		this.dados = dados;
	}
	
	public void setDados(Dados dados) {
		this.dados.add(dados);
	}
}
