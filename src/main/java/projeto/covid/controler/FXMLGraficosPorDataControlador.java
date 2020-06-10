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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Dados;
import projeto.covid.modelo.auxilio.DadosGraficos;
import projeto.covid.modelo.auxilio.Nacao;

public class FXMLGraficosPorDataControlador implements TelaMudanca {

	private Nacao entidade;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	@FXML
	private LineChart<String, Number> lineChart;

	@FXML
	private Label dataAtual;

	@FXML
	private Slider sliderData;

	@FXML
	private Label dataMax;

	private List<String> datas;
	private Integer graficoEscolhido;

	@FXML
	private void initialize() {
		Principal.addTelaMudanca(this);
	}
	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.GRAFICOS)) {
			this.entidade = (Nacao) dados;
			this.graficoEscolhido = 0;
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + entidade);
			carregarDatas();
			atualizarGrafico();
		}
	}
	@FXML
	private void botaoVoltar(ActionEvent event) {
		this.lineChart.getData().clear();
		this.datas = null;
		this.sliderData.setValue(0);
		Principal.trocarTela(Telas.PRINCIPAL);
	}

	@FXML
	void mudarDataMin(MouseEvent event) {
		dataAtual.setText(this.datas.get(Double.valueOf(sliderData.getValue()).intValue()));
	}

	@FXML
	void limitesGraficos(MouseEvent event) {
		mudarDataMin(null);
		atualizarGrafico();
	}

	@FXML
	private void botaoCasos(ActionEvent event) {
		graficoEscolhido = 0;
		atualizarGrafico();
	}

	@FXML
	void botaoCasosAcumulados(ActionEvent event) {
		graficoEscolhido = 1;
		atualizarGrafico();
	}

	@FXML
	void botaoCasosNovos(ActionEvent event) {
		graficoEscolhido = 2;
		atualizarGrafico();
	}

	@FXML
	void botaoObitos(ActionEvent event) {
		graficoEscolhido = 3;
		atualizarGrafico();
	}

	@FXML
	void botaoObitosAcumulados(ActionEvent event) {
		graficoEscolhido = 4;
		atualizarGrafico();
	}

	@FXML
	void botaoObitosNovos(ActionEvent event) {
		graficoEscolhido = 5;
		atualizarGrafico();
	}



	private void carregarDatas() {
		this.datas = new ArrayList<String>();
		for (Dados dado : this.entidade.getDados()) {
			this.datas.add(dado.getDataString());
		}
		this.dataAtual.setText(this.datas.get(0));
		this.dataMax.setText(this.datas.get(this.datas.size() - 1));
		this.sliderData.setMin(0);
		this.sliderData.setMax(this.datas.size() - 1);
	}

	private void configuracoesGraficos(String titulo, String eixoX, String eixoY) {
		this.lineChart.setTitle(titulo + " - " + entidade.getNome());
		this.lineChart.setAnimated(true);
		this.xAxis.setLabel(eixoX);
		this.yAxis.setLabel(eixoY);
		this.xAxis.setAnimated(false);
	}

	private void gerarGraficos(Series<String, Number> series) {
		this.lineChart.getData().clear();
		this.lineChart.getData().add(series);
	}

	private void gerarGraficos(List<Series<String, Number>> series) {
		this.lineChart.getData().clear();
		this.lineChart.setData(FXCollections.observableArrayList(series));
	}

	private void atualizarGrafico() {
		int comeco = Double.valueOf(sliderData.getValue()).intValue();

		switch (graficoEscolhido.intValue()) {
		case 0:
			List<Series<String, Number>> seriesCasos = new ArrayList<XYChart.Series<String, Number>>();
			seriesCasos.add(DadosGraficos.seriesCasosAcumulados(entidade.getDados(), comeco));
			seriesCasos.add(DadosGraficos.seriesCasosNovos(entidade.getDados(), comeco));
			configuracoesGraficos("Casos novos e acumulados de COVID-19 por data de notificação", "Data da Notificacao",
					"Casos");
			gerarGraficos(seriesCasos);
			break;
		case 1:
			configuracoesGraficos("Casos acumulados de COVID-19 por data de notificação", "Data da Notificacao",
					"Casos Acumulados");
			gerarGraficos(DadosGraficos.seriesCasosAcumulados(entidade.getDados(), comeco));
			break;
		case 2:
			configuracoesGraficos("Casos novos de COVID-19 por data de notificação", "Data da Notificacao", "Casos");
			gerarGraficos(DadosGraficos.seriesCasosNovos(entidade.getDados(), comeco));
			break;
		case 3:
			List<Series<String, Number>> seriesObitos = new ArrayList<XYChart.Series<String, Number>>();
			seriesObitos.add(DadosGraficos.seriesObitosAcumulados(entidade.getDados(), comeco));
			seriesObitos.add(DadosGraficos.seriesObitosNovos(entidade.getDados(), comeco));
			configuracoesGraficos("Obitos novos e acumulados de COVID-19 por data de notificação",
					"Data da Notificacao", "Obitos");
			gerarGraficos(seriesObitos);
			break;
		case 4:
			configuracoesGraficos("Obitos acumulados de COVID-19 por data de notificação", "Data da Notificacao",
					"Obitos Acumulados");
			gerarGraficos(DadosGraficos.seriesObitosAcumulados(entidade.getDados(), comeco));
			break;
		case 5:
			configuracoesGraficos("Obitos novos de COVID-19 por data de notificação", "Data da Notificacao", "Casos");
			gerarGraficos(DadosGraficos.seriesObitosNovos(entidade.getDados(), comeco));
		}
	}
}
