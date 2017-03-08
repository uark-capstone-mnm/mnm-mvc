/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.GraphModel;
import interfaces.GraphMonitor;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author thy
 */
public class SeriesChartPane extends JPanel implements GraphMonitor {

    private GraphModel model;
    private XYChart chart;

    public SeriesChartPane(GraphModel model) {
        this.model = model;
        chart = initChart();

        List<Double>[] sineData = model.getData();
        chart.addSeries("sine", sineData[0], sineData[1]);
        setLayout(new BorderLayout());

        XChartPanel chartPane = new XChartPanel(chart);
        add(chartPane);

        UpdateWorker worker = new UpdateWorker(this);
        worker.execute();
    }

    @Override
    public GraphModel getModel() {
        return model;
    }

    @Override
    public void updateData(List<Double>[] data) {
        chart.updateXYSeries("sine", data[0], data[1], null);
        repaint();
    }

    private XYChart initChart() {
        XYChart chart = new XYChartBuilder().width(300).height(200).theme(Styler.ChartTheme.GGPlot2).build();
        chart.getStyler().setChartPadding(0);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTitlesVisible(false);
        return chart;
    }

}