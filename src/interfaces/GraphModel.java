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
 * Class Interface to insert data into graphs
 */
public interface GraphModel {
	/**
	 * Use random data
	 * @return
	 * @throws FileNotFoundException
	 */
    public List<Double>[] getData() throws FileNotFoundException;
    /**
     * Use passed data as ArrayLists
     * @param time ArrayList of time intervals
     * @param data ArrayList of data points
     * @return
     * @throws FileNotFoundException
     */
    public List<Double>[] getData(ArrayList<Double> time, ArrayList<Double> data) throws FileNotFoundException;
    
    
    public void cleanData(List<Double>[] data);
}
