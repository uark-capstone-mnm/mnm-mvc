/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.NIRS;

import interfaces.GraphModel;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thy
 */
public class NIRSGraphModelA implements GraphModel{
    
    @Override
    public List<Double>[] getData() throws FileNotFoundException{
        String file = "./src/data/NIRS.txt";
        //String file = "101random_fl_2.txt"; //EEG Data File
        //ParsingEEGtxt parsing = new ParsingEEGtxt(file);
        //parsing.printDataExternally();
        ParsingNIRStxt fileToParse = new ParsingNIRStxt(file);
        ArrayList<Double> xData = fileToParse.attributes.get(0).getData();
        ArrayList<Double> yData = fileToParse.attributes.get(5).getData();
        return new List[] {xData, yData};
    }

    @Override
    public List<Double>[] getData(ArrayList<Double> time, ArrayList<Double> data) throws FileNotFoundException {
        String file = "../data/NIRS.txt";
        //String file = "101random_fl_2.txt"; //EEG Data File
        //ParsingEEGtxt parsing = new ParsingEEGtxt(file);
        //parsing.printDataExternally();
        ParsingNIRStxt fileToParse = new ParsingNIRStxt(file);
        ArrayList<Double> xData = fileToParse.attributes.get(0).getData();
        ArrayList<Double> yData = fileToParse.attributes.get(1).getData();
        return new List[] {xData, yData};
    }

    @Override
    public void cleanData(List<Double>[] data) {
        if(data[0].size() > 100 && data[1].size() > 100){
            data[0].subList(0, 100).clear();
            data[1].subList(0, 100).clear();
        }
    }   
}