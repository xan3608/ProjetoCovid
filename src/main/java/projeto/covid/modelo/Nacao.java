package projeto.covid.modelo;

import java.util.List;

public abstract class Nacao {
	public abstract List<Dados> getDados();
	public abstract void setDados(Dados dados);
	public abstract void setDados(List<Dados> ListaDados);
	public abstract String getNome();
}
