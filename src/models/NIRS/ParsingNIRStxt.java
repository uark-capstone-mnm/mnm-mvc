package models.NIRS; 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Model to parse NIRS data
 *
 */
public class ParsingNIRStxt {

    private String file;
    private String header;
    private String acquisationInformation;
    private String fileInformation;
    private String calibrationState;
    private String auxCalibrationValues;
    private String wfCalibrationValues;
    private String distanceSettings;
    private String dataBegins;
    public ArrayList<NIRSDataAttribute> attributes;
    private Scanner scan;
	
    /**
     * Constructs a ParsingNIRStxt object.
     * @param file name of NIRS file to be parsed
     * @throws FileNotFoundException
     */
    public ParsingNIRStxt(String file) throws FileNotFoundException {
        this.setFile(file);
        this.attributes = new ArrayList<NIRSDataAttribute>();
        this.parsing();		
    }
	
    /**
     * Parse the given NIRS file at the new line (for added header information in NIRS data file) 
     * @param scan
     * @param attribute
     * @return
     */
    private String parsingBlock(Scanner scan, String attribute) {
        String scannedData = null;
        while (scan.hasNextLine()) {
            scannedData = scan.nextLine();    	
            if (attribute == null)
                attribute = scannedData;
            else 
                attribute = attribute + "\n" +scannedData;
            if (scannedData.isEmpty()) 
                break;       	
        }
        return attribute;
    }
	
    /**
     * Parses the header information of the NIRS data file
     * @param scan
     */
    private void parsingHeader(Scanner scan)
    {
        header = parsingBlock (scan, header);
//        System.out.println(header);
    }

    /**
     * Parses the acquisition information of the NIRS data file
     * @param scan
     */
    private void parsingAcquisationInformation(Scanner scan)
    {
        acquisationInformation = parsingBlock (scan, acquisationInformation);
//        System.out.println(acquisationInformation);
    }

    /**
     * Parses the file information of the NIRS data file
     * @param scan
     */
    private void parsingFileInformation(Scanner scan)
    {
        fileInformation = parsingBlock (scan, fileInformation);
//        System.out.println(fileInformation);
    }

    /**
     * Parses the calibration state of the NIRS data file
     * @param scan
     */
    private void parsingCalibrationState(Scanner scan)
    {
        calibrationState = parsingBlock (scan, calibrationState);
//        System.out.println(calibrationState);
    }
    
    /**
     * Parses the aux calibrartion values of the NIRS data file
     * @param scan
     */
    private void parsingauxCalibrationValues(Scanner scan)
    {
        calibrationState = parsingBlock (scan, calibrationState);
        calibrationState = parsingBlock (scan, calibrationState);
//        System.out.println(auxCalibrationValues);
    }
    
    /**
     * Parses the wfCalibration values of the NIRS data file
     * @param scan
     */
    private void parsingwfCalibrationValues(Scanner scan)
    {
        wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
        wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
        wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
        wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
        wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
//        System.out.println(wfCalibrationValues);
    }

    /**
     * Parses the distance values of the NIRS data file
     * @param scan
     */
    private void parsingDistanceSettings(Scanner scan)
    {
        distanceSettings = parsingBlock (scan, distanceSettings);
        distanceSettings = parsingBlock (scan, distanceSettings);
        distanceSettings = parsingBlock (scan, distanceSettings);
        distanceSettings = parsingBlock (scan, distanceSettings);
        distanceSettings = parsingBlock (scan, distanceSettings);
//        System.out.println(distanceSettings);
    }

    /**
     * Parses the NIRS data file based on tab
     * @throws FileNotFoundException
     */
    private void parsing() throws FileNotFoundException
    {
        String scannedData;
        scan = new Scanner (new File(file));
        String[] returnValue = null;
        String regularExpression = "\t";

        this.parsingHeader( scan);
        //scannedData = scan.nextLine();

        this.parsingAcquisationInformation(scan);

        this.parsingFileInformation(scan);

        this.parsingCalibrationState(scan);

        scan.nextLine();

        this.parsingauxCalibrationValues(scan);

        scan.nextLine();

        this.parsingwfCalibrationValues(scan);

        this.parsingDistanceSettings(scan);

        scan.nextLine();

        scan.nextLine();

//        System.out.println("\n"+scan.nextLine());
        scan.nextLine();
        dataBegins = parsingBlock (scan, dataBegins);

        returnValue = dataBegins.split(regularExpression);

        for (int i = 0 ; i < returnValue.length ; i++)
        {
            NIRSDataAttribute attribute = new NIRSDataAttribute (returnValue[i]);
            attributes.add(attribute);
        }


        while (scan.hasNextLine())
        {

            scannedData = scan.nextLine();

            if (scannedData.equals("#DATA ENDS"))
            {
                break;
            }

            returnValue = scannedData.split(regularExpression);

            for (int i = 0 ; i < returnValue.length; i++)
            {
               attributes.get(i).addData(Double.parseDouble(returnValue[i]));
            }  

        }   
    }

    /**
     * DEBUGGING: Print data to PrintWriter object
     */
    public void printDataExternally () {
        PrintWriter writer = null;
        for (int i = 0 ; i < attributes.size() ; i++) {
            File output = new File ("Receptor "+i+".txt");
            try{
                if (output.exists() == false)
                    output.createNewFile();
                writer = new PrintWriter (new FileWriter(output, true));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            writer.println("Receptor #" + i);
            attributes.get(i).printData(writer);
            writer.println("Fetching of Receptor's #"+i+ " Data is Done");
            writer.close();
        }
    }

    /**
     * DEBUGGING: Print data to screen
     */
    public void printData () {
        for (int i = 0 ; i < attributes.size() ; i++) {
            System.out.println(attributes.get(i).getAttributeName());
            attributes.get(i).printData();
            System.out.println("Fetching of "+attributes.get(i).getAttributeName()+ " Data is Done");
        }
    }

    /**
     * Return NIRS file name
     * @return
     */
    public String getFile() {
        return file;
    }

    /**
     * Set NIRS file name
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Returns header information contained in NIRS file
     * @return
     */
    public String getHeader() {
        return header;
    }

    /**
     * Sets header information contained in NIRS file
     * @param header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Returns acquisition information in NIRS file
     * @return
     */
    public String getAcquisationInformation() {
        return acquisationInformation;
    }

    /**
     * Sets acquisition information in NIRS file
     * @param acquisationInformation
     */
    public void setAcquisationInformation(String acquisationInformation) {
        this.acquisationInformation = acquisationInformation;
    }

    /**
     * Returns file information in NIRS file
     * @return
     */
    public String getFileInformation() {
        return fileInformation;
    }

    /**
     * Sets file information in NIRS file
     * @param fileInformation
     */
    public void setFileInformation(String fileInformation) {
        this.fileInformation = fileInformation;
    }

    /**
     * Returns calibration state of NIRS file
     * @return
     */
    public String getCalibrationState() {
        return calibrationState;
    }

    /**
     * Sets calibration state of NIRS file
     * @param calibrationState
     */
    public void setCalibrationState(String calibrationState) {
        this.calibrationState = calibrationState;
    }

    /**
     * Returns aux calibration values of NIRS file
     * @return
     */
    public String getAuxCalibrationValues() {
        return auxCalibrationValues;
    }

    /**
     * Sets aux calibration values of NIRS file
     * @param auxCalibrationValues
     */
    public void setAuxCalibrationValues(String auxCalibrationValues) {
        this.auxCalibrationValues = auxCalibrationValues;
    }

    /**
     * Returns distance settings of NIRS file
     * @return
     */
    public String getDistanceSettings() {
        return distanceSettings;
    }

    /**
     * Sets distance settings of NIRS file
     * @param distanceSettings
     */
    public void setDistanceSettings(String distanceSettings) {
        this.distanceSettings = distanceSettings;
    }

    /**
     * Returns data begins (String)
     * @return
     */
    public String getDataBegins() {
        return dataBegins;
    }

    /**
     * Set data begins (String)
     * @param dataBegins
     */
    public void setDataBegins(String dataBegins) {
        this.dataBegins = dataBegins;
    }

    /**
     * Returns list of attributes of all NIRSDataAttributes
     * @return
     */
    public ArrayList<NIRSDataAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Sets list of attribute of all NIRSDataAttributes
     * @param attributes
     */
    public void setAttributes(ArrayList<NIRSDataAttribute> attributes) {
        this.attributes = attributes;
    }

}
