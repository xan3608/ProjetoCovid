package projeto.covid;

import java.io.IOException;
import java.net.URISyntaxException;

import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.Dados;
import projeto.covid.modelo.GrupoEstados;
import projeto.covid.modelo.GrupoMunicipios;
import projeto.covid.modelo.Municipio;
import projeto.covid.planilha.LeituraPlanilha;
import projeto.covid.planilha.Selenium;
import projeto.covid.recursos.DiretorioTemp;

public class Teste {
	public static void main(String[] args) throws IOException, URISyntaxException {
		DiretorioTemp diretorio = new DiretorioTemp();
		//diretorio.extrairParaTemp();
		Brasil brasil = new Brasil();
		GrupoEstados e = new GrupoEstados();
		GrupoMunicipios m = new GrupoMunicipios();
//		//Selenium selenium = new Selenium(diretorio);
//		try{
//			//selenium.downloadDados();
//		}catch (Exception ex) {
//			System.out.println("Erro ao baixar os dados");
//			return;
//		}
		
		
		//LeituraPlanilha dp = new LeituraPlanilha(diretorio, selenium.getDownloadName());
		LeituraPlanilha dp = new LeituraPlanilha(diretorio, "HIST_PAINEL_COVIDBR_20200517.xlsx");
		dp.lerDados(brasil, e, m);
		Municipio mm = m.buscarMunicipio("novo gama");
		for(Dados d : mm.getGrupoDados()) {
			System.out.println(d);
		}
		System.out.println(mm);
		System.out.println("\n\n");
		System.out.println(brasil);
		System.out.println(m);
		System.out.println(e);
	}
}
