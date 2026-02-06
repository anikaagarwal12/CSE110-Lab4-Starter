package edu.ucsd.spendingtracker.view.charts;

import edu.ucsd.spendingtracker.model.Category;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

import java.util.Map;

public class PieChartProvider implements IChartProvider {

    @Override
    public Node createChart(Map<Category, Double> data) {
        PieChart chart = new PieChart();

        data.forEach((cat, sum) -> {
            chart.getData().add(
                new PieChart.Data(cat.name(), sum)
            );
        });

        chart.setLegendVisible(true);
        return chart;
    }

    @Override
    public String getDisplayName() {
        return "Pie Chart";
    }
}
