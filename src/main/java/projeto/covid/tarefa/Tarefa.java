package projeto.covid.tarefa;

import java.io.IOException;

import projeto.covid.modelo.GrupoBrasil;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.planilha.DadosPlanilha;
import projeto.covid.planilha.OrganizaDadosDaPlanilha;
import projeto.covid.recursos.DiretorioTemp;

public class Tarefa implements Runnable {

	@Override
	public void run() {

		DiretorioTemp diretorio = new DiretorioTemp();
//		try {
//			diretorio.extrairParaTemp();
//		} catch (IOException | URISyntaxException e) {
//			e.printStackTrace();
//			System.out.println("Erro ao extrair para diretorio temporario");
//		}

//		Selenium selenium = new Selenium(diretorio);
//		selenium.downloadDados();
//		System.out.println(selenium.getDownloadName());
		
		
		GrupoBrasil gb = new GrupoBrasil();
		GrupoEstado ge = new GrupoEstado();
		GrupoMunicipio gm = new GrupoMunicipio();
		
		DadosPlanilha dadoPlanilha = new DadosPlanilha(diretorio, "HIST_PAINEL_COVIDBR_20200517.xlsx", gb, ge, gm);
		try {
			dadoPlanilha.lerDados();
			OrganizaDadosDaPlanilha.organizarDados(dadoPlanilha.getDadosDasLinhas(), gb, ge, gm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
