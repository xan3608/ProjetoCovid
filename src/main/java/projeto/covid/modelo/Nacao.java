package projeto.covid.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Nacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomePais;
	private List<Dados> dados;
	private List<Estado> estados;
	private List<Municipio> municipios;

	public Nacao(String nomePais) {
		this.setNomePais(nomePais);
		this.dados = new ArrayList<Dados>();
		this.estados = new ArrayList<Estado>();
		this.municipios = new ArrayList<Municipio>();
	}

	// Metodos Assessores
	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
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

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void setEstados(Estado estado) {
		this.estados.add(estado);
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public void setMunicipios(Municipio municipio) {
		this.municipios.add(municipio);
	}

	public Municipio buscarMunicipioPorNome(String nome) {
		for(int i = this.municipios.size()-1; i>=0 ; i--) {
			if(municipios.get(i).getNomeMunicipio().equals(nome.trim().toUpperCase())){
				return municipios.get(i);
			}
		}
		return null;
	}

	public Estado buscarEstadoPorNome(String nome) {
		for(int i = this.estados.size()-1; i>=0 ; i--) {
			if(estados.get(i).getNomeEstado().equals(nome.trim().toUpperCase())){
				return estados.get(i);
			}
		}
		return null;
	}
	public Dados buscarDadosPorData(String data) {
		for(Dados dado: this.dados) {
			if(dado.getData().equals(data))
				return dado;
		}
		return null;
	}
}
