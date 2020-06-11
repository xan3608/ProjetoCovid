package projeto.covid.modelo.auxilio;

import java.io.Serializable;
import java.util.List;

import projeto.covid.modelo.Dados;

public abstract class Nacao implements Serializable {
	public abstract List<Dados> getDados();
	public abstract void setDados(Dados dados);
	public abstract void setDados(List<Dados> ListaDados);
	public abstract String getNome();
}
