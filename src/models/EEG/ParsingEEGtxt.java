package models.EEG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;
import models.Receptor;

/**
 * Model to parse EEG data
 *
 */
public class ParsingEEGtxt {

    private String file;
    private ArrayList<Receptor> receptorsEEG;
    private Scanner scan;

    /**
     * Constructs a ParsingEEGtxt object
     * @param file name of EEG file to be parsed
     * @throws FileNotFoundException
     */
    public ParsingEEGtxt(String file) throws FileNotFoundException {
        this.setFile(file);
        this.receptorsEEG = new ArrayList<Receptor>();
        this.parsing();
    }

    /**
     * Reads in the file and parses the EEG data into receptorsEEG (ArrayList of Receptors)
     * @throws FileNotFoundException
     */
    private void parsing() throws FileNotFoundException {
        String scannedData;
        scan = new Scanner (new File(file));
        String[] returnValue = null;
        String regularExpression = "\t";

        scannedData = scan.nextLine();

        returnValue = scannedData.split(regularExpression);

        for (int i = 0 ; i < returnValue.length; i++) {
            Receptor E = new Receptor(i);
            receptorsEEG.add(E);
            E.addData(Double.parseDouble(returnValue[i]));
        }

        while (scan.hasNextLine()) {
            scannedData = scan.nextLine();
            returnValue = scannedData.split(regularExpression);

            for (int i = 0 ; i < returnValue.length; i++) {
                receptorsEEG.get(i).addData(Double.parseDouble(returnValue[i]));
            }  
        }
    }

    /**
     * DEBUGGING: Print data to PrintWriter object
     */
    public void printDataExternally () {
        PrintWriter writer = null;
        //Timestamp timeStamp = new Timestamp (System.currentTimeMillis()); 
        String path = "EEG";
        File directory = new File (path);
        directory.mkdir();
        for (int i = 0 ; i < receptorsEEG.size() ; i++)
        {
            File output = new File (path + "/Receptor "+i+".txt");
            try{
                if (output.exists() == false)
                    output.createNewFile();
                writer = new PrintWriter (new FileWriter(output, true));
            } catch (IOException e){
                e.printStackTrace();
            }
            writer.println("Receptor #" + i);
            receptorsEEG.get(i).printData(writer);
            writer.println("Fetching of Receptor's #"+i+ " Data is Done");
            writer.close();
        }
    }

    /**
     * DEBUGGING: Print data to screen.
     */
    public void printData () {
        for (int i = 0 ; i < receptorsEEG.size() ; i++)
        {
            System.out.println("Receptor #" + i);
            receptorsEEG.get(i).printData();
            System.out.println("Fetching of Receptor's #"+i+ " Data is Done");
        }
    }

    /**
     * Return the EEG filename
     * @return
     */
    public String getFile() {
        return file;
    }
    
    /**
     * Set the EEG filename
     * @param file
     */
    public void setFile(String file) {
        this.file = file;
    }
}
