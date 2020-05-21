package projeto.covid.modelo.database.planilha;

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

import projeto.covid.modelo.Pais;
import projeto.covid.modelo.auxilio.Grupo;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipio;
import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class LeituraPlanilha {
	private Path arquivoPath;

	public LeituraPlanilha(DiretorioTemp diretorio, String arquivoNome) {
		this.arquivoPath = diretorio.getBrowserDownload().resolve(arquivoNome).toAbsolutePath();
	}

	public void lerDados(Pais pais, GrupoEstado estados, GrupoMunicipio municipios) throws IOException {
		File arquivo = this.arquivoPath.toFile();
		FileInputStream fis = new FileInputStream(arquivo);
		XSSFWorkbook planilha = new XSSFWorkbook(fis);
		XSSFSheet pagina = planilha.getSheetAt(0);
		lerDadosPagina(pagina, pais, estados, municipios);
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
						linhaGenerica.getDados().setData(celulaString(celula));
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
						break;
					}
					}
				}
				OrganizaDadosDaPlanilha.organizarDados(linhaGenerica, pais, estados, municipios);
			} catch (Exception e) {
				System.err.println("Falha ao ler a coluna na linha: " + linhaind);
				System.out.println(linhaGenerica);
			}
			linhaind++;
		}
	}

	private Integer celulaNumerica(Cell celula) {
		try {
			return Integer.valueOf(celula.getStringCellValue());
		} catch (IllegalStateException | NumberFormatException e) {
			return Integer.valueOf(Double.valueOf(celula.getNumericCellValue()).intValue());
		}
	}

	private String celulaString(Cell celula) {
		try {
			return celula.getStringCellValue();
		} catch (IllegalStateException e) {
			return Double.valueOf(celula.getNumericCellValue()).toString();
		}
	}

}
