package projeto.covid.modelo;

import java.util.ArrayList;
import java.util.List;

import projeto.covid.modelo.auxilio.Nacao;

public class Pais extends Nacao {
	private List<Dados> dados;
	private String nomePais;

	Pais() {
		this.dados = new ArrayList<Dados>();
	}

	public Pais(String nome) {
		this.setNomePais(nome);
		this.dados = new ArrayList<Dados>();
	}

	@Override
	public String toString() {
		return "Brasil, Dados: " + dados.size();
	}

	@Override
	public List<Dados> getDados() {
		return this.dados;
	}

	@Override
	public String getNome() {
		return getNomePais();
	}

	@Override
	public void setDados(Dados dados) {
		this.dados.add(dados);
	}

	@Override
	public void setDados(List<Dados> ListaDados) {
		this.dados = ListaDados;
	}

	public String getNomePais() {
		return this.nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais.toUpperCase().trim();
	}

}
