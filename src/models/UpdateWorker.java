/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.GraphMonitor;
import java.util.List;
import javax.swing.SwingWorker;
import org.knowm.xchart.XYChart;

/**
 *
 * @author thy
 */
public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private GraphMonitor monitor;
    private XYChart chart;

    public UpdateWorker(GraphMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(100);
            publish(monitor.getModel().getData());
        }
    }

    @Override
    protected void process(List<List<Double>[]> chunks) {
        for (List<Double>[] data : chunks) {
            monitor.updateData(data);
        }
    }

}
