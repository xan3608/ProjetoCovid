package projeto.covid.modelo.database.scraping;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javafx.scene.control.TextArea;
import projeto.covid.modelo.Nacao;
import projeto.covid.modelo.database.planilha.OrganizaDadosCsv;

public class ApiCsv {

	private final String URL = "https://brasil.io/dataset/covid19/caso_full/?format=csv";
	private Scanner ler;

	public void resquestDados(TextArea console, Nacao nacao) {
		console.appendText("Buscando dados do Brasil.IO\n");
		long inicio = System.currentTimeMillis();
		try {
			URLConnection conexao = new URL(URL).openConnection();
			conexao.setRequestProperty("User-Agent", "Mozilla/5.0");
			conexao.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			ler = new Scanner(conexao.getInputStream());

			ler.nextLine();
			console.appendText("Carregando dados do Brasil.IO\n");
			while (ler.hasNext()) {
				String[] dados = ler.nextLine().split(",");
				OrganizaDadosCsv.organizaDados(dados, nacao);
			}
		} catch (IOException e) {
			e.printStackTrace();
			console.appendText("Erro ao carregar os dados\n");
		} finally {
			if (ler != null) {
				ler.close();
			}
			System.out.println("Tempo de execução: " + (System.currentTimeMillis() - inicio) / 1000);
			console.appendText("Dados carregados com sucesso\n");
		}
	}
}
