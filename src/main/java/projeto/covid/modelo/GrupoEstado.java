package projeto.covid.modelo;

public class GrupoEstado extends Grupo {

	public Estado buscarEstado(String nomeEstado) {
		for (Nacao estado : super.getGrupo()) {
			if (estado.getNome().equalsIgnoreCase(nomeEstado.trim())) {
				return (Estado) estado;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Estados cadastrados: " + super.getGrupo().size();
	}
}
