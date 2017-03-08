/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import javax.swing.JPanel;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 *
 * @author thy
 */
public class Graph{    

    public static XYChart getEmptyXYChart() {
        XYChart chart = new XYChartBuilder().width(300).height(200).theme(ChartTheme.GGPlot2).build();
        chart.getStyler().setChartPadding(0);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTitlesVisible(false);
        return chart;
    }
    
    public static JPanel getEmptyGraph() {
        return new XChartPanel(getEmptyXYChart());
    }
    
    public static JPanel getGraphFromData(double[] xData, double[] yData) {
        XYChart chart = getEmptyXYChart();
        XYSeries data = chart.addSeries("eeg data", xData, yData);
        return new XChartPanel(chart);
    }
    
    // Create a method that takes x-axis as Time and y-axis as ArrayList data
    public static JPanel getGraphFromData(ArrayList<Double> yData) {
        XYChart chart = getEmptyXYChart();
//        XYSeries data = chart.addSeries("eeg data", xData, yData);
        return new XChartPanel(chart);
    }
    
    // Testing purposes
    public static JPanel getRandomStaticGraph() {
        XYChart chart = getEmptyXYChart();
        
        XYSeries series = chart.addSeries("eeg", null, getRandomWalk(200));
        series.setMarker(SeriesMarkers.NONE);
        return new XChartPanel(chart);
    }
    
    // Testing purposes
    public static JPanel getRandomRealTimeGraph() throws InterruptedException {
        XYChart chart = getEmptyXYChart();
        
        // Show it
        final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();
        
        double phase = 0;
        double[][] initdata = getSineData(phase);
        XYSeries series = chart.addSeries("eeg", null, getRandomWalk(200));
        while(true) {
            phase += 2 * Math.PI * 2 / 20.0;
            
            Thread.sleep(100);
            final double[][] data = getSineData(phase);
            
            chart.updateXYSeries("sine", data[0], data[1], null);
            sw.repaintChart();
            
        }
        
    }
    // To be used to generate random static graphs (testing purposes)
    private static double[] getRandomWalk(int numPoints) {
        double[] y = new double[numPoints];
        y[0] = 0;
        for(int i = 1; i < y.length; i++)
            y[i] = y[i-1] + Math.random() - .5;
        return y;
    }
    
    // To be used to generate real time graphs (testing purposes)
    private static double[][] getSineData(double phase) {
        double[] xData = new double[100];
        double[] yData = new double[100];
        for(int i = 0; i < xData.length; i++) {
            double radians = phase + (2 * Math.PI / xData.length * i);
            xData[i] = radians;
            yData[i] = Math.sin(radians);
        }
        return new double[][] {xData, yData};
    }
}
