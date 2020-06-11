package projeto.covid.modelo;

public class Municipio extends Estado {
	private String nomeMunicipio;

	public Municipio() {
		super();
	}

	public Municipio(String municipio, String estado, Dados dados) {
		super(estado, dados);
		this.setMunicipio(municipio);
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

}