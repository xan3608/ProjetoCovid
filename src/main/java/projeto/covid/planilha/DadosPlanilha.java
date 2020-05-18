package projeto.covid.planilha;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import projeto.covid.modelo.GrupoBrasil;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.recursos.DiretorioTemp;

public class DadosPlanilha {
	// private DiretorioTemp diretorio;
	private Path arquivoPath;
	private String arquivoNome;
	private GrupoBrasil grupoBrasil;
	private GrupoEstado grupoEstado;
	private GrupoMunicipio grupoMunicipio;
	private List<DadosLinha> dadosDasLinhas;

	public DadosPlanilha(DiretorioTemp diretorio, String arquivoNome, GrupoBrasil grupoBrasil, GrupoEstado grupoEstado,
			GrupoMunicipio grupoMunicipio) {
		this.arquivoNome = arquivoNome;
		this.grupoBrasil = grupoBrasil;
		this.grupoEstado = grupoEstado;
		this.grupoMunicipio = grupoMunicipio;
		this.arquivoPath = diretorio.getBrowserDownload().resolve(this.arquivoNome).toAbsolutePath();
	}

//	public DadosPlanilha() {
//		this.diretorio = new DiretorioTemp();
//		this.arquivoNome = "HIST_PAINEL_COVIDBR_20200516.xlsx";
//		this.arquivoPath = this.diretorio.getBrowserDownload().resolve(this.arquivoNome).toAbsolutePath();
//		this.grupoBrasil = new GrupoBrasil();
//		this.grupoEstado = new GrupoEstado();
//		this.grupoMunicipio = new GrupoMunicipio();
//	}

	public void lerDados() throws IOException {
		File arquivo = this.arquivoPath.toFile();
		FileInputStream fis = new FileInputStream(arquivo);
		XSSFWorkbook planilha = new XSSFWorkbook(fis);
		XSSFSheet pagina = planilha.getSheetAt(0);
		dadosDasLinhas = new ArrayList<DadosLinha>();

		lerDadosPagina(pagina, dadosDasLinhas);
		planilha.close();
		OrganizaDadosDaPlanilha.organizarDados(dadosDasLinhas, grupoBrasil, grupoEstado, grupoMunicipio);
	}

	private void lerDadosPagina(XSSFSheet pagina, List<DadosLinha> grupo) {
		Iterator<Row> linhasIterator = pagina.iterator();

		while (linhasIterator.hasNext()) {
			Row linha = linhasIterator.next();
			Iterator<Cell> celulasIterator = linha.iterator();
			DadosLinha linhaGenerica = new DadosLinha();

			try {
				while (celulasIterator.hasNext()) {
					Cell celula = celulasIterator.next();
					switch (celula.getColumnIndex()) {
					case 0: {// Coluna de regiao
						linhaGenerica.setRegiao(celula.getStringCellValue());
						break;
					}
					case 1: { // estado
						linhaGenerica.setEstado(celula.getStringCellValue());
						break;
					}
					case 2: { // municipio
						linhaGenerica.setMunicipio(celula.getStringCellValue());
						break;
					}
					case 6: { // RegiaoSaude
						linhaGenerica.setRegiaoSaude(celula.getStringCellValue());
						break;
					}
					case 7: { // data
						linhaGenerica.setData(celula.getStringCellValue());
						break;
					}
					case 8: { // SemanaEpidemia
						linhaGenerica.setSemanaEpidemia(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					case 9: { // populacao
						linhaGenerica.setPopulacao(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					case 10: { // casosAcumulados
						linhaGenerica.setCasosAcumulados(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					case 11: { // obitosAcumulados
						linhaGenerica.setObitosAcumulados(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					}
				}
				grupo.add(linhaGenerica);
			} catch (NumberFormatException e) {
				System.err.println("Falha ao ler a coluna");
			}
		}
	}

//	public static void main(String[] args) {
//		try {
//			new DadosPlanilha().lerDados();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
}
