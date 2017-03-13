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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import models.PatientFile.PatientFile;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import views.View;

/**
 *
 * @author thy
 */
public class Controller {
    PatientFile model = new PatientFile();

    public void startApplication() {
        View view = new View();
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
    
    public void implementRegionImages(JPanel parent, JTabbedPane tabbed) {
        try{ 
            ArrayList<BufferedImage> abiRegions = new ArrayList<BufferedImage>();
            ArrayList<ImageIcon> aiRegions = new ArrayList<ImageIcon>();
            ArrayList<JLabel> ajlRegions = new ArrayList<JLabel>();
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
