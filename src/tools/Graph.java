/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.List;
import javax.swing.JPanel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 *
 * @author thy
 */
public class Graph{    

    public static JPanel getEmptyGraph() {
        XYChart chart = new XYChartBuilder().width(300).height(200).build();
        chart.getStyler().setChartPadding(0);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTitlesVisible(false);
        return new XChartPanel(chart);

    }
    
    public static JPanel getRandomStaticGraph() {
        XYChart chart = new XYChartBuilder().width(300).height(200).build();
        chart.getStyler().setChartPadding(0);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTitlesVisible(false);
        
        XYSeries series = chart.addSeries("eeg", null, getRandomWalk(200));
        series.setMarker(SeriesMarkers.NONE);
        return new XChartPanel(chart);
    }

    private static double[] getRandomWalk(int numPoints) {
        double[] y = new double[numPoints];
        y[0] = 0;
        for(int i = 1; i < y.length; i++)
            y[i] = y[i-1] + Math.random() - .5;
        return y;
    }
}
