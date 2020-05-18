package projeto.covid.modelo;

public class Municipio extends Estado {
	private String municipio, regiaoSaude;

	public Municipio() {
		super();
	}

	public Municipio(String municipio, String regiaoSaude, String estado, String regiao) {
		super(estado, regiao);
		this.setMunicipio(municipio);
		this.setRegiaoSaude(regiaoSaude);
	}

	@Override
	public String toString() {
//		return "Municipio: " + this.municipio + ", RegiaoSaude: " + this.regiaoSaude + ", " + super.toString();
		return String.format("%-20s%-10s", super.getEstado(), this.municipio);
//		return super.getEstado() + " - " +  this.municipio;
	}
	
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio.toUpperCase().trim();
	}

	public String getRegiaoSaude() {
		return regiaoSaude;
	}

	public void setRegiaoSaude(String regiaoSaude) {
		this.regiaoSaude = regiaoSaude.toUpperCase().trim();
	}
}