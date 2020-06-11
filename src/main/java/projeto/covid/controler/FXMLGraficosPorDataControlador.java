package projeto.covid.controler;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.auxilio.DadosGraficos;
import projeto.covid.modelo.auxilio.Nacao;

public class FXMLGraficosControlador implements TelaMudanca {

	private Nacao entidade;

	@FXML
	private CategoryAxis xAxis = new CategoryAxis();

	@FXML
	private NumberAxis yAxis = new NumberAxis();

	@FXML
	private LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

	@FXML
	private void initialize() {
		Principal.addTelaMudanca(this);
	}

	@FXML
	private void botaoVoltar(ActionEvent event) {
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	private void botaoCasos(ActionEvent event) {
		List<Series<String, Number>> series = new ArrayList<XYChart.Series<String, Number>>();
		series.add(DadosGraficos.seriesCasosNovos(entidade.getDados()));
		series.add(DadosGraficos.seriesCasosAcumulados(entidade.getDados()));
		setInformacoesGraficos("Casos novos e acumulados de COVID-19 por data de notificação",
								"Data da Notificacao", "Casos");
		gerarGraficos(series);
	}

	@FXML
	void botaoCasosAcumulados(ActionEvent event) {
		setInformacoesGraficos("Casos acumulados de COVID-19 por data de notificação",
				"Data da Notificacao", "Casos");
		gerarGraficos(DadosGraficos.seriesCasosAcumulados(entidade.getDados()));
	}

	@FXML
	void botaoCasosNovos(ActionEvent event) {
		setInformacoesGraficos("Casos novos de COVID-19 por data de notificação",
				"Data da Notificacao", "Casos");
		gerarGraficos(DadosGraficos.seriesCasosNovos(entidade.getDados()));
	}
	
    @FXML
    void botaoObitos(ActionEvent event) {

    }

    @FXML
    void botaoObitosAcumulados(ActionEvent event) {

    }

    @FXML
    void botaoObitosNovos(ActionEvent event) {

    }

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.GRAFICOS)) {
			this.entidade = (Nacao) dados;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + entidade);
			botaoCasos(null);
		}
	}

	private void setInformacoesGraficos(String titulo, String eixoX, String eixoY) {
		this.lineChart.setTitle(titulo + " - " + entidade.getNome());
		this.xAxis.setLabel(eixoX);
		this.yAxis.setLabel(eixoY);
	}

	private void gerarGraficos(Series<String, Number> series) {
		this.lineChart.getData().clear();
		this.lineChart.getData().add(series);
		System.out.println("graph data: " + lineChart.getData().size());
	}

	private void gerarGraficos(List<Series<String, Number>> series) {
		this.lineChart.getData().clear();
		this.lineChart.setData(FXCollections.observableArrayList(series));
		System.out.println("graph data: " + lineChart.getData().size());
	}
}
