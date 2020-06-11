package projeto.covid.controler;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import projeto.covid.controler.auxilio.TelaMudanca;
import projeto.covid.controler.auxilio.Telas;
import projeto.covid.controler.principal.Principal;
import projeto.covid.modelo.Dados;
import projeto.covid.modelo.Estado;
import projeto.covid.modelo.auxilio.DadosGraficos;

public class FXMLGraficosPorDataControlador implements TelaMudanca {

	private Estado entidade;

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
	private String graficoEscolhido;

	@FXML
	private void initialize() {
		Principal.addTelaMudanca(this);
	}

	@Override
	public void mudouTela(Telas novaTela, Object dados) {
		if (novaTela.equals(Telas.GRAFICOS)) {
			this.entidade = (Estado) dados;
			this.graficoEscolhido = "Casos";
			System.out.println("Nova Tela: " + novaTela + ", Dados: " + this.entidade);
			carregarDatas();
			atualizarGrafico();
		}
	}

	@FXML
	private void botaoVoltar(ActionEvent event) {
		this.lineChart.getData().clear();
		this.datas = null;
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
	private void menuItemGrafico(ActionEvent event) {
		MenuItem button = (MenuItem) event.getSource();
		this.sliderData.setValue(0);
		this.setGraficoEscolhido(button.getText());
	}

	@FXML
	private void botaoSplitGrafico(ActionEvent event) {
		SplitMenuButton button = (SplitMenuButton) event.getSource();
		this.sliderData.setValue(0);
		this.setGraficoEscolhido(button.getText());
	}

	private void carregarDatas() {
		this.datas = new ArrayList<String>();
		for (Dados dado : this.entidade.getDados()) {
			this.datas.add(dado.getData());
		}
		this.dataAtual.setText(this.datas.get(0));
		this.dataMax.setText(this.datas.get(this.datas.size() - 1));
		this.sliderData.setMin(0);
		this.sliderData.setMax(this.datas.size() - 1);
	}

	private void configuracoesGraficos(String titulo, String eixoX, String eixoY) {
		this.lineChart.setTitle(titulo + " - " + this.entidade);
		this.lineChart.setAnimated(true);
		this.xAxis.setLabel(eixoX);
		this.yAxis.setLabel(eixoY);
		this.xAxis.setAnimated(false);
	}

	private void mouseEventsGrafico(List<Series<String, Number>> series) {
		for (Series<String, Number> serie : series) {
			for (XYChart.Data<String, Number> data : serie.getData()) {
				data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, (e) -> {
					Tooltip.install(data.getNode(), new Tooltip(this.graficoEscolhido + ": " + data.getYValue()
							+ "\nData: " + data.getXValue()));
				});
			}
		}
	}

	private void gerarGraficos(List<Series<String, Number>> series) {
		this.lineChart.getData().clear();
		this.lineChart.setData(FXCollections.observableArrayList(series));
		this.mouseEventsGrafico(series);
	}

	public void setGraficoEscolhido(String grafico) {
		this.graficoEscolhido = grafico;
		this.atualizarGrafico();
	}

	private void atualizarGrafico() {
		List<Series<String, Number>> series = new ArrayList<XYChart.Series<String, Number>>(1);
		int comeco = Double.valueOf(sliderData.getValue()).intValue();

		switch (graficoEscolhido) {
		case "Casos":
			configuracoesGraficos("Casos novos e acumulados de COVID-19 por data de notificação", "Data da Notificacao",
					"Casos");
			series.add(DadosGraficos.seriesCasosNovos(entidade.getDados(), comeco));
			series.add(DadosGraficos.seriesCasosAcumulados(entidade.getDados(), comeco));
			break;
		case "Casos Acumulados":
			configuracoesGraficos("Casos acumulados de COVID-19 por data de notificação", "Data da Notificacao",
					"Casos Acumulados");
			series.add(DadosGraficos.seriesCasosAcumulados(entidade.getDados(), comeco));
			break;
		case "Casos Novos":
			configuracoesGraficos("Casos novos de COVID-19 por data de notificação", "Data da Notificacao", "Casos");
			series.add(DadosGraficos.seriesCasosNovos(entidade.getDados(), comeco));
			break;
		case "Obitos":
			configuracoesGraficos("Obitos novos e acumulados de COVID-19 por data de notificação",
					"Data da Notificacao", "Obitos");
			series.add(DadosGraficos.seriesObitosAcumulados(entidade.getDados(), comeco));
			series.add(DadosGraficos.seriesObitosNovos(entidade.getDados(), comeco));
			break;
		case "Obitos Acumulados":
			configuracoesGraficos("Obitos acumulados de COVID-19 por data de notificação", "Data da Notificacao",
					"Obitos Acumulados");
			series.add(DadosGraficos.seriesObitosAcumulados(entidade.getDados(), comeco));
			break;
		case "Obitos Novos":
			configuracoesGraficos("Obitos novos de COVID-19 por data de notificação", "Data da Notificacao", "Casos");
			series.add(DadosGraficos.seriesObitosNovos(entidade.getDados(), comeco));
		}
		gerarGraficos(series);
	}
}