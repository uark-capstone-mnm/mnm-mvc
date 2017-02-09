/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author thy
 */
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class PatientFile {
    private String fileName;
    private int iSSN;
    BufferedWriter writer;
    JTextField fName, lName;
    JFormattedTextField ssn;
            
    public boolean createPatientFile() throws ParseException {
        
        fName = new JTextField();
        lName = new JTextField();
        
        MaskFormatter mfSSN = new MaskFormatter("###-##-####");
        mfSSN.setPlaceholderCharacter('_');
        mfSSN.setValidCharacters("0123456789");
        ssn = new JFormattedTextField(mfSSN);
        
        
        final JComponent[] inputs = new JComponent[] {
                new JLabel("First"),
                fName,
                new JLabel("Last"),
                lName,
                new JLabel("SSN"),
                ssn
        };
        
        int result = JOptionPane.showConfirmDialog(null, inputs, "Information", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(validateNameInput(fName.getText()) && validateNameInput(lName.getText()) && validateSSNInput(ssn.getText()))
            {
                return createFile();
            }
            else {
                JOptionPane.showMessageDialog(null,"Invalid Information","Information",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            System.out.println("User canceled / closed the dialog, result = " + result);
            return false;
        }
    }
    
    public boolean createFile() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileTime = new SimpleDateFormat("MMM dd yyyy 'at' HH:mm:ss").format(new Date());
        // Create directory if doesn't exist
        File patientsDir = new File("./src/patients");
        if(!patientsDir.exists()) {
            System.out.println("Creating \"patients \" directory...");
            try {
                patientsDir.mkdir();
            }
            catch(SecurityException e) {
                System.out.println("Unable to make directory due to a SecurityException.");
                return false;
            }
            
        }
        fileName = "./src/patients/" + fName.getText() + "_" + lName.getText() + "_" + timeStamp + ".txt";

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            writer.write("Patient: " + lName.getText() + "," + fName.getText());
            writer.newLine();
            writer.write("SSN: " + "***-**-" + ssn.getText().substring(7));
            writer.newLine();
            writer.write("Start Recording: " + fileTime);
            writer.newLine();
            writer.write("--------------------------------------------------------------------------------------------");
            writer.newLine();
            writer.close();
        } catch (Exception er) {
            return false;
        }
        return true;
    }
   
    public boolean closePatientFile() {
        String fileTime = new SimpleDateFormat("MMM dd yyyy 'at' HH:mm:ss").format(new Date());
        try{
            FileWriter fstream = new FileWriter(fileName, true);
            writer = new BufferedWriter(fstream);
            writer.write("--------------------------------------------------------------------------------------------");
            writer.newLine();
            writer.write("End Recording: " + fileTime);
            writer.close();
            return true;
        } catch (Exception er) {
            System.out.print("Error in closePatientFile");
            return false;
        }
    }

    public int transformSSN(String sSSN) {
        String temp = sSSN.replaceAll("-", "");
        return Integer.parseInt(temp);
    }

    private boolean validateNameInput(String text) {
        if (text.isEmpty())
            return false;
        for(char c : text.toCharArray())
            if(!Character.isLetter(c) && c != '-' && c != ' ')
                return false;
        return true;
    }

    private boolean validateSSNInput(String text) {
        if (text.isEmpty())
            return false;
        if(text.length() < 9)
            return false;
        return true;
    }
}