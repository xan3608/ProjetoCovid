package projeto.covid.modelo.auxilio;

import java.util.List;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import projeto.covid.modelo.Dados;

public class DadosGraficos {

	public static Series<String, Number> seriesCasosAcumulados(List<Dados> dados, int comeco) {
		Series<String, Number> series = new Series<String, Number>();
		int indice = 0;

		series.setName("Casos Acumulados");
		for (Dados dado : dados) {
			if (indice++ >= comeco) {
				series.getData().add(new XYChart.Data<String, Number>(dado.getData(), dado.getCasosAcumulados()));
			}
		}
		return series;
	}

	public static Series<String, Number> seriesCasosNovos(List<Dados> dados, int comeco) {
		Series<String, Number> series = new Series<String, Number>();
		series.setName("Casos Novos");

		for (int i = 1; i < dados.size(); i++) {
			if (i >= comeco) {
				int novosCasos = (dados.get(i).getCasosAcumulados() - dados.get(i - 1).getCasosAcumulados());
				novosCasos = (novosCasos < 0) ? 0 : novosCasos;
				series.getData().add(new Data<String, Number>(dados.get(i).getData(), novosCasos));
			}
		}
		return series;
	}

	public static Series<String, Number> seriesObitosAcumulados(List<Dados> dados, int comeco) {
		Series<String, Number> series = new Series<String, Number>();
		int indice = 0;

		series.setName("Obitos Acumulados");
		for (Dados dado : dados) {
			if (indice++ >= comeco) {
				series.getData().add(new XYChart.Data<String, Number>(dado.getData(), dado.getObitosAcumulados()));
			}
		}
		return series;
	}

	public static Series<String, Number> seriesObitosNovos(List<Dados> dados, int comeco) {
		Series<String, Number> series = new Series<String, Number>();

		series.setName("Obitos Novos");
		for (int i = 1; i < dados.size(); i++) {
			if (i >= comeco) {
				Integer novosCasos = (dados.get(i).getObitosAcumulados() - dados.get(i - 1).getObitosAcumulados());
				novosCasos = (novosCasos < 0) ? 0 : novosCasos;
				series.getData().add(new Data<String, Number>(dados.get(i).getData(), novosCasos));
			}
		}
		return series;
	}
}
