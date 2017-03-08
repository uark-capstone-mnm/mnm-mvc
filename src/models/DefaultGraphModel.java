/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.GraphModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thy
 */
public class DefaultGraphModel implements GraphModel{
    private double phase = 0;
    @Override
    public List<Double>[] getData() {
        List<Double> xData = new ArrayList<Double>();
        List<Double> yData = new ArrayList<Double>();
        for(int i = 0; i < 100; i++) {
            xData.add((double)i);
            Random rgen = new Random();
            yData.add(rgen.nextDouble());
        }
        return new List[]{xData, yData};
    }

    @Override
    public List<Double>[] getData(ArrayList<Double> data) {
        List<Double> xData = new ArrayList<Double>();
        List<Double> yData = new ArrayList<Double>();
        // Implement deep copy or pointer or call the arrayLists to be sent to the graphs
        return new List[]{xData, yData};
    }

    
}
