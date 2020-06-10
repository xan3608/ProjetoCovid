package projeto.covid.modelo;

import projeto.covid.modelo.auxilio.Grupo;
import projeto.covid.modelo.auxilio.Nacao;

public class GrupoEstado extends Grupo {

	public Nacao buscarEstado(String nomeEstado) {
		if(super.getGrupo().isEmpty()) {
			return null;
		}
		
		if(super.getGrupo().get(super.getGrupo().size()-1).getNome().equals(nomeEstado.trim().toUpperCase()))
			return super.getGrupo().get(super.getGrupo().size()-1);
//		for (int i = super.getGrupo().size()-1; i >= 0; i--) {
//			Nacao estado = super.getGrupo(i);
//			if (estado.getNome()) {
//				return (Estado) estado;
//			}
//		}
		return null;	
	}

	@Override
	public String toString() {
		return "Estados cadastrados: " + super.getGrupo().size();
	}
}
