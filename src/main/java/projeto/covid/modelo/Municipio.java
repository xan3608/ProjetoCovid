package projeto.covid.modelo;

public class Municipio extends Estado {
	private String nomeMunicipio;

	public Municipio(String municipio, String estado, Dados dados) {
		super(estado, dados);
		this.setNomeMunicipio(municipio);
	}

	@Override
	public String getNome() {
		return this.getNomeMunicipio();
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio.trim().toUpperCase();
	}
}