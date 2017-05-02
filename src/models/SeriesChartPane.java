/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.GraphModel;
import interfaces.GraphMonitor;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;

/**
 * Panel of real time graphs.
 *
 */
public class SeriesChartPane extends JPanel implements GraphMonitor {

    private GraphModel model;
    private XYChart chart;

    /**
     * Constructs a SeriesChartPane object.
     * @param model
     * @throws FileNotFoundException 
     */
    public SeriesChartPane(GraphModel model) throws FileNotFoundException {
        this.model = model;
        chart = initChart();

        List<Double>[] sineData = model.getData();
        chart.addSeries("eeg", sineData[0], sineData[1]);
        setLayout(new BorderLayout());

        XChartPanel chartPane = new XChartPanel(chart);
        add(chartPane);

        UpdateWorker worker = new UpdateWorker(this);
        worker.execute();
    }

    /**
     * Returns the SeriesChartPane.model
     */
    @Override
    public GraphModel getModel() {
        return model;
    }

    /**
     * Updates the data and refreshes it to display new data
     */
    @Override
    public void updateData(List<Double>[] data) {
        chart.updateXYSeries("eeg", data[0], data[1], null);
        repaint();
    }

    /**
     * Basic characteristics for a real-time graph<br>
     * Set dimensions=300x200<br>
     * No padding, no legends, no axis titles, no markers
     * @return
     */
    private XYChart initChart() {
        XYChart chart = new XYChartBuilder().width(300).height(200).build();
        chart.getStyler().setChartPadding(0);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTitlesVisible(false);
        chart.getStyler().setMarkerSize(0);
        return chart;
    }

}