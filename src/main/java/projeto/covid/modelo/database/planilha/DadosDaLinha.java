package projeto.covid.modelo.database.planilha;

import projeto.covid.modelo.Dados;

public class DadosDaLinha {
	private String regiao, estado, municipio, regiaoSaude;
	private Integer codMunicipio;
	private Dados dados;

	public DadosDaLinha() {
		this.dados = new Dados();
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

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getRegiaoSaude() {
		return regiaoSaude;
	}

	public void setRegiaoSaude(String regiaoSaude) {
		this.regiaoSaude = regiaoSaude;
	}

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}

	public Integer getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(Integer codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

}
