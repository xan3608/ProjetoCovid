package projeto.covid.modelo;

import java.util.ArrayList;
import java.util.List;

public class GrupoBrasil {
	private List<Brasil> grupoBrasil;
	
	public GrupoBrasil(){
		this.grupoBrasil = new ArrayList<Brasil>();
	}

	public List<Brasil> getGrupoBrasil() {
		return grupoBrasil;
	}

	public void setGrupoBrasil(Brasil brasil) {
		this.grupoBrasil.add(brasil);
	}
}
