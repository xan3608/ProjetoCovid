package projeto.covid.modelo;

public class Municipio extends Estado {
	private String municipio, regiaoSaude;

	
	@Override
	public String toString() {
		return super.toString() + " Municipio: '" + this.municipio + "' RegiaoSaude: " + this.regiaoSaude;
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
}
