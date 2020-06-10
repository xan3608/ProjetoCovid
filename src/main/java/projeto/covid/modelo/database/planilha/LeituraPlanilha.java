package projeto.covid.modelo.database.planilha;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.Pais;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class LeituraPlanilha {
	private Path arquivoPath;

	public LeituraPlanilha(DiretorioTemp diretorio, String arquivoNome) {
		this.arquivoPath = diretorio.getBrowserDownload().resolve(arquivoNome).toAbsolutePath();
	}

	public void lerDados(Pais pais, GrupoEstado estados, GrupoMunicipio municipios) throws IOException {
		long tempini = System.currentTimeMillis();
		File arquivo = this.arquivoPath.toFile();
		System.out.println("Abrindo planilha");
		FileInputStream fis = new FileInputStream(arquivo);
		System.out.println(arquivo);
		XSSFWorkbook planilha = new XSSFWorkbook(fis);

		System.out.println("Planilha aberta");
		XSSFSheet pagina = planilha.getSheetAt(0);
		System.out.println("Lendo planilha");
		lerDadosPagina(pagina, pais, estados, municipios);
		System.out.println("Tempo decorrido : " + (System.currentTimeMillis() - tempini) / 1000 + " segundos");
		planilha.close();
		fis.close();
		planilha = null;
	}

	private void lerDadosPagina(XSSFSheet pagina, Pais pais, GrupoEstado estados, GrupoMunicipio municipios) {
		Iterator<Row> linhasIterator = pagina.iterator();
		int linhaind = 1;

		linhasIterator.next(); // pula cabeçalho
		while (linhasIterator.hasNext()) {
			Row linha = linhasIterator.next();
			Iterator<Cell> celulasIterator = linha.iterator();
			DadosDaLinha linhaGenerica = new DadosDaLinha(linhaind);
			try {
				while (celulasIterator.hasNext()) {
					Cell celula = celulasIterator.next();
					switch (celula.getColumnIndex()) {
					case 0: {// Coluna de regiao
						linhaGenerica.setRegiao(celulaString(celula));
						break;
					}
					case 1: { // estado
						linhaGenerica.setEstado(celulaString(celula));
						break;
					}
					case 2: { // municipio
						linhaGenerica.setMunicipio(celulaString(celula));
						break;
					}
					case 4: { // codMunicipio
						linhaGenerica.setCodMunicipio(celulaNumerica(celula));
					}
					case 6: { // RegiaoSaude
						linhaGenerica.setRegiaoSaude(celulaString(celula));
						break;
					}
					case 7: { // data
						linhaGenerica.getDados().setData(celulaDate(celula));
						break;
					}
					case 8: { // SemanaEpidemia
						linhaGenerica.getDados().setSemanaEpidemia(celulaNumerica(celula));
						break;
					}
					case 9: { // populacao
						linhaGenerica.getDados().setPopulacao(celulaNumerica(celula));
						break;
					}
					case 10: { // casosAcumulados
						linhaGenerica.getDados().setCasosAcumulados(celulaNumerica(celula));
						break;
					}
					case 11: { // obitosAcumulados
						linhaGenerica.getDados().setObitosAcumulados(celulaNumerica(celula));
					}
					}
				}
				//System.out.println(linhaGenerica);
				System.out.println(linhaind);
				OrganizaDadosDaPlanilha.organizarDados(linhaGenerica, pais, estados, municipios);
			} catch (IllegalStateException | NumberFormatException e) {
				System.err.println(linhaGenerica);
				System.err.println(linhaGenerica.getDados());
			}
			linhaind++;
		}
	}

	private GregorianCalendar celulaDate(Cell celula) {
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(celula.getDateCellValue());
		return data;
	}

	private Integer celulaNumerica(Cell celula) {
		if (celula.getCellType() == CellType.STRING) {
			return Integer.valueOf(celula.getStringCellValue().replaceAll("[(][0-9][)]|[.]", ""));
		}
		return Integer.valueOf(Double.valueOf(celula.getNumericCellValue()).intValue());
	}

	private String celulaString(Cell celula) {
		if (celula.getCellType() == CellType.NUMERIC) {
			return Double.valueOf(celula.getNumericCellValue()).toString();
		}
		return celula.getStringCellValue();
	}

}
