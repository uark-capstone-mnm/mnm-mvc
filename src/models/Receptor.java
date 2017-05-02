package models;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Receptor object to keep track of information for each EEG receptor.
 *
 */
public class Receptor {

    private int ID;
    private ArrayList<Double> data;

    /**
     * Constructs a Receptor object
     * @param ID integer ID of NIRS LED
     */
    public Receptor (int ID)
    {
        this.ID = ID ;
        this.data = new ArrayList<Double>();

    }

    /**
     * For a given LED, store the data
     */
    public void addData (Double receivedData)
    {
        this.data.add(receivedData);
    }

    /**
     * DEBUGGING: Print data to screen
     */
    public void printData ()
    {
        for (int i = 0 ; i < data.size() ; i++)
        {
            System.out.println("Data Entry #" + i + " " + data.get(i).toString());
        }
    }

    /**
     * DEBUGGING: Print data to PrintWriter object
     * @param writer
     */
    public void printData (PrintWriter writer)
    {
        for (int i = 0 ; i < data.size() ; i++)
        {
            writer.println("Data Entry #" + i + " " + data.get(i).toString());
        }
    }

    /**
     * Return NIRS receptor ID for Receptor object
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Set NIRS receptor ID for Receptor object
     * @param iD
     */
    public void setID(int iD) {
        ID = iD;
    }

    /**
     * Return values for Receptor object
     * @return
     */
    public ArrayList<Double> getData() {
        return data;
    }

    /**
     * Set values for Receptor object
     * @param data
     */
    public void setData(ArrayList<Double> data) {
        this.data = data;
    }
	
}
