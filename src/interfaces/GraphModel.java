/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thy
 */
public interface GraphModel {
    public List<Double>[] getData() throws FileNotFoundException;
    public List<Double>[] getData(ArrayList<Double> time, ArrayList<Double> data) throws FileNotFoundException;
    public void cleanData(List<Double>[] data);
}
