package projeto.covid;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Pais;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class Teste {
	public static void main(String[] args) throws IOException, URISyntaxException {
//		DiretorioTemp diretorio = new DiretorioTemp();
//		//diretorio.extrairParaTemp();
//		Pais brasil = new Pais();
//		GrupoEstado e = new GrupoEstado();
//		GrupoMunicipio m = new GrupoMunicipio();
//		//Selenium selenium = new Selenium(diretorio);
//		try{
//			//selenium.downloadDados();
//		}catch (Exception ex) {
//			System.out.println("Erro ao baixar os dados");
//			return;
//		}
		
		
		//LeituraPlanilha dp = new LeituraPlanilha(diretorio, selenium.getDownloadName());
//		LeituraPlanilha dp = new LeituraPlanilha(diretorio, "HIST_PAINEL_COVIDBR_18mai2020.xlsx");
//		dp.lerDados(brasil, e, m);
//		Municipio mm = m.buscarMunicipio("novo gama");
//		for(Dados d : mm.getGrupo()) {
//			System.out.println(d);
//		}
//		System.out.println(mm);
//		System.out.println("\n\n");
//		System.out.println(brasil);
//		System.out.println(m);
//		System.out.println(e);
		ArrayList<Integer> a =new ArrayList<Integer>(1);
		a.add(55);
		a.add(55);

		System.out.println(a.size());
		System.out.println(a);
	}
}
