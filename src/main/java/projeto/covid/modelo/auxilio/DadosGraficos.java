package projeto.covid.modelo.auxilio;

import java.util.List;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import projeto.covid.modelo.Dados;

public class DadosGraficos {

	public static Series<String, Number> seriesCasosAcumulados(List<Dados> dados) {
		Series<String, Number> series = new Series<String, Number>();
		series.setName("Casos Acumulados");

		for (Dados dado : dados) {
			series.getData().add(new XYChart.Data<String, Number>(dado.getData(), dado.getCasosAcumulados()));
		}
		return series;
	}

	public static Series<String, Number> seriesCasosNovos(List<Dados> dados) {
		Series<String, Number> series = new Series<String, Number>();
		series.setName("Casos Novos");

		for (int i = 1; i < dados.size(); i++) {
			Integer novosCasos = (dados.get(i).getCasosAcumulados() - dados.get(i - 1).getCasosAcumulados());
			novosCasos = (novosCasos < 0) ? 0 : novosCasos;
			series.getData().add(new Data<String, Number>(dados.get(i).getData(), novosCasos));
		}
		return series;
	}

	public static Series<String, Number> seriesObitosAcumulados(List<Dados> dados) {
		Series<String, Number> series = new Series<String, Number>();
		series.setName("Obitos Acumulados");

		for (Dados dado : dados) {
			series.getData().add(new XYChart.Data<String, Number>(dado.getData(), dado.getObitosAcumulados()));
		}
		return series;
	}

	public static Series<String, Number> seriesObitosNovos(List<Dados> dados) {
		Series<String, Number> series = new Series<String, Number>();
		series.setName("Obitos Novos");

		for (int i = 1; i < dados.size(); i++) {
			if (dados.get(i).getObitosAcumulados() > 0) {
				Integer novosCasos = (dados.get(i).getObitosAcumulados() - dados.get(i - 1).getObitosAcumulados());
				novosCasos = (novosCasos < 0) ? 0 : novosCasos;
				series.getData().add(new Data<String, Number>(dados.get(i).getData(), novosCasos));
			}
		}

		return series;
	}
}
