package projeto.covid.modelo;

public class Municipio extends Estado {
	private String nomeMunicipio, nomeRegiaoSaude;

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
		return String.format("%-30s%-10s", this.nomeMunicipio, super.getNomeEstado());
	}

	@Override
	public String getNome() {
		return getMunicipio();
	}

	@Override
	public int compareTo(Estado e) {
		return this.nomeMunicipio.compareToIgnoreCase(e.getNome());
	}

	public String getMunicipio() {
		return this.nomeMunicipio;
	}

	public void setMunicipio(String municipio) {
		this.nomeMunicipio = municipio.toUpperCase().trim();
	}

	public String getRegiaoSaude() {
		return this.nomeRegiaoSaude;
	}

	public void setRegiaoSaude(String regiaoSaude) {
		this.nomeRegiaoSaude = regiaoSaude.toUpperCase().trim();
	}
}