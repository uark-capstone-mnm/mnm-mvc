package models.NIRS;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * NIRSDataAttribute object to keep track of information for each NIRS receptor.
 *
 */
public class NIRSDataAttribute {

	private String attributeName;
	private ArrayList<Double> data;
	
	/**
	 * Constructs a NIRSDataAttribute object
	 * @param attributeName
	 */
	public NIRSDataAttribute (String attributeName)
	{
            this.setAttributeName(attributeName);
            this.data = new ArrayList<Double>();
	}
	
	/**
	 * DEBUGGING: Print data to screen
	 */
	public void printData ()
	{
            for (int i = 0 ; i < data.size() ; i++)
            {
                System.out.println(attributeName + " " + i + " " + data.get(i).toString());
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
                writer.println(attributeName + " " + i + " " + data.get(i).toString());
            }
	}
	
	/**
	 * Add data to given NIRSDataAttribute
	 * @param receivedData
	 */
	public void addData (Double receivedData)
	{
            this.data.add(receivedData);
	}

	/**
	 * Return NIRS Attribute Name
	 * @return
	 */
	public String getAttributeName() {
            return attributeName;
	}

	/**
	 * Set NIRS Attribute Name
	 * @param attributeName
	 */
	public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
	}

	/**
	 * Returns values for NIRSDataAttribute object
	 * @return
	 */
	public ArrayList<Double> getData() {
            return data;
	}

	/**
	 * Set values for NIRSDataAttribute object
	 * @param data
	 */
	public void setData(ArrayList<Double> data) {
            this.data = data;
	}	
}
