/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.ParseException; // Remove with NIRS tester
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent; // Remove with NIRS tester
import javax.swing.JFormattedTextField; // Remove with NIRS tester
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Remove with NIRS tester
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.MaskFormatter; // Remove with NIRS tester

import models.BrainOverlay;
import models.Configuration;
import models.PatientFile.PatientFile;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import views.View;
import tools.SoundCritical;
import tools.SoundWarning;

/**
 *
 * @author thy
 */
public class Controller {
    PatientFile model = new PatientFile();
    BrainOverlay bOverlay = new BrainOverlay();
    
    public int region;
    public int color;
    
    // Moved these outside of creation so I could access them to change the images
    ArrayList<BufferedImage> abiRegions = new ArrayList<BufferedImage>();
    ArrayList<ImageIcon> aiRegions = new ArrayList<ImageIcon>();
    ArrayList<JLabel> ajlRegions = new ArrayList<JLabel>();

    public void startApplication() {
        View view = new View();
        Configuration customProps = new Configuration("config.ini");
        System.out.println("Yellow Threshold: " + customProps.getThreshold("yellow"));
        System.out.println("Red Threshold: " + customProps.getThreshold("red"));
        view.setVisible(true);
    }

    public boolean startRecording() {
        try{
            return model.createPatientFile();
        } catch (Exception er) {
            return false;
        }
    }

    public boolean stopRecording() {
        try{
            return model.closePatientFile();
        } catch (Exception er) {
            return false;
        }
    }
    
    
    /* Calls setImage function from BrainOverlay and sets the region and color
     * based on user inputs. This will obviously change once we have a listener
     * based on user inputs.  This will obviously change once we have a listener
     * on the NIRS data, but this is for demo'ing the ability to change the image
     * at a given region
     */
    public void changeBrainRegion() throws ParseException{
    	JFormattedTextField r, c;
    	// Mask for reduction of user input errors for testing only
    	MaskFormatter mf = new MaskFormatter("#");
    	mf.setValidCharacters("0123456789");
    	r = new JFormattedTextField(mf);
    	c = new JFormattedTextField(mf);
    	final JComponent[] inputs = new JComponent[] { 
    			new JLabel("Region (0-7)"), r, new JLabel("Color - 1 = Yellow; 2 = Red; Else = Green"), c
    			};
    	
    	// Pop-up input panel
    	JOptionPane.showConfirmDialog(null, inputs, "Test Case", JOptionPane.PLAIN_MESSAGE);
    	region = Integer.parseInt(r.getText());
    	color = Integer.parseInt(c.getText());
    	
    	// Does the actual work of replacing the image
    	BufferedImage brainImage = bOverlay.setImage(region, color);
    	ajlRegions.get(region).setIcon(new ImageIcon(brainImage));
        View.updateRegion();
    	if (color == 1){
    		try {
    			new SoundWarning().start();
    		} catch (Exception e){
    			System.out.println(e);
    		}
                
    	}
    	else if (color == 2){
    		try {
    			new SoundCritical().start();
    		} catch (Exception e){
    			System.out.println(e);
    		}
    	}
    }
    
    // Color - 1 = Yellow, 2 = Red, Else = Green
    // Region - Starts counting from 0
    public void changeBrainRegion(int color, int region) throws ParseException{
    	BufferedImage brainImage = bOverlay.setImage(region, color);
    	ajlRegions.get(region).setIcon(new ImageIcon(brainImage));
    	if (color == 1){
    		try {
    			new SoundWarning().start();
    		} catch (Exception e){
    			System.out.println(e);
    		}
    	}
    	else if (color == 2){
    		try {
    			new SoundCritical().start();
    		} catch (Exception e){
    			System.out.println(e);
    		}
    	}
        
        
    }

    public void implementRegionImages(JPanel parent, JTabbedPane tabbed) {
        try{ 
            
            GridBagConstraints bgbc = new GridBagConstraints();
            int brainy = 0;
            File[] greenImgs = new File("./src/images/").listFiles((FileFilter) new PrefixFileFilter("green", IOCase.INSENSITIVE));
            for(File file : greenImgs) {
                abiRegions.add(ImageIO.read(file));
            }
            for (BufferedImage region : abiRegions) {
                aiRegions.add(new ImageIcon(region));
            }
            for(ImageIcon region: aiRegions) {
                ajlRegions.add(new JLabel(region));
            }
            for(int i = 0; i < ajlRegions.size(); i++) {
                ajlRegions.get(i).addMouseListener(new mlRegion(tabbed, i));
                bgbc = new GridBagConstraints();
                bgbc.insets = new Insets(0, 0, 0, 0);
                if( i == 0 )
                {
                    bgbc.anchor = GridBagConstraints.LINE_START;

                }
                else if (i % 2 == 1) {
                    bgbc.gridx = 1;
                    bgbc.gridy = brainy;
                    bgbc.fill = GridBagConstraints.HORIZONTAL;
                    bgbc.weightx = 1.0;
                }
                else if (i % 2 == 0) {
                    bgbc.gridx = 0;
                    bgbc.gridy = ++brainy;
                    bgbc.anchor = GridBagConstraints.LINE_START;

                }
                parent.add(ajlRegions.get(i), bgbc);
            }
        }
        catch (IOException ex) {
            System.out.println("Cannot load image...");
        }
    }
    
}
