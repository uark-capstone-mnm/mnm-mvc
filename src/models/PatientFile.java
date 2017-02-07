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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author AfzaalAhmad
 */
public class PatientFile {
    private String fileName;
    BufferedWriter writer;

    public boolean createPatientFile() {
        
        JTextField fName = new JTextField();
        JTextField lName = new JTextField();
        JTextField ssn = new JTextField();
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
            System.out.println("You entered " +
                    fName.getText() + ", " +
                    lName.getText() + ", " +
                    ssn.getText());
        } else {
            System.out.println("User canceled / closed the dialog, result = " + result);
        }
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
            writer.write("SSN: " + ssn.getText());
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

}