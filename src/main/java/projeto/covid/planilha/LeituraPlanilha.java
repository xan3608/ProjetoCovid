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

import projeto.covid.modelo.Brasil;
import projeto.covid.modelo.GrupoEstado;
import projeto.covid.modelo.GrupoMunicipios;
import projeto.covid.recursos.DiretorioTemp;

public class LeituraPlanilha {
	private Path arquivoPath;

	public LeituraPlanilha(DiretorioTemp diretorio, String arquivoNome) {
		this.arquivoPath = diretorio.getBrowserDownload().resolve(arquivoNome).toAbsolutePath();
	}

	public void lerDados(Brasil brasil, GrupoEstado GrupoEstados, GrupoMunicipios GrupoMunicipios) throws IOException {
		File arquivo = this.arquivoPath.toFile();
		FileInputStream fis = new FileInputStream(arquivo);
		XSSFWorkbook planilha = new XSSFWorkbook(fis);
		XSSFSheet pagina = planilha.getSheetAt(0);
		List<DadosDaLinha> dadosDasLinhas = new ArrayList<DadosDaLinha>();

		lerDadosPagina(pagina, dadosDasLinhas);
		planilha.close();
		OrganizaDadosDaPlanilha.organizarDados(dadosDasLinhas, brasil, GrupoEstados, GrupoMunicipios);
	}

	private void lerDadosPagina(XSSFSheet pagina, List<DadosDaLinha> dadosDasLinhas) {
		Iterator<Row> linhasIterator = pagina.iterator();

		
		linhasIterator.next(); // pula cabeçalho
		int linhaind = 1;
		while (linhasIterator.hasNext()) {
			Row linha = linhasIterator.next();
			Iterator<Cell> celulasIterator = linha.iterator();
			DadosDaLinha linhaGenerica = new DadosDaLinha();
			//System.out.println("linha : " + linhaind);
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
						//System.out.println("data: '" + celula.getStringCellValue() + "'");
						linhaGenerica.getDados().setData(celula.getStringCellValue());
						break;
					}
					case 8: { // SemanaEpidemia
						//System.out.println("Semana Epidemia: '" +  celula.getStringCellValue() + "'");
						linhaGenerica.getDados().setSemanaEpidemia(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					case 9: { // populacao
						linhaGenerica.getDados().setPopulacao(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					case 10: { // casosAcumulados
						linhaGenerica.getDados().setCasosAcumulados(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					case 11: { // obitosAcumulados
						linhaGenerica.getDados().setObitosAcumulados(Integer.valueOf(celula.getStringCellValue()));
						break;
					}
					}
				}
				dadosDasLinhas.add(linhaGenerica);
			} catch (NumberFormatException e) {
				System.err.println("Falha ao ler a coluna na linha: " + linha);
			}
			linhaind++;
		}
	}

}
