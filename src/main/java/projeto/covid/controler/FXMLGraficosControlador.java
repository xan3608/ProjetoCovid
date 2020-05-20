package projeto.covid.controler;

import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class FXMLGraficosControlador {
	@FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private Axis<Number> yAxis = new NumberAxis();

    @FXML
    private BarChart<String, Number> lineChart = new BarChart<String, Number>(xAxis, yAxis);

    @FXML

    private void initialize() {
    	yAxis.setLabel("Eixo Y");
        xAxis.setLabel("Eixo X");
        Series<String, Number> series = new Series<String, Number>();
        
        series.getData().add(new XYChart.Data<String, Number>("oi", 5));	
	
        lineChart.getData().add(series);
    }

}
