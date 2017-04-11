/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import tools.Global;
import interfaces.GraphModel;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BrainOverlay;
import models.NIRS.DefaultGraphModel;
import models.SeriesChartPane;
import tools.ImageMap;


/**
 *
 * @author thy
 */
public class View extends javax.swing.JFrame {
    Controller cont = new Controller();
    /**
     * Creates new form view
     */
    public View() {
        initComponents();
        cont.startRecording();
        start.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabBrain = new javax.swing.JPanel();
        leftMain = new javax.swing.JPanel();
        rightMain = new javax.swing.JPanel();
        tabEEG = new javax.swing.JPanel();
        leftEEG = new javax.swing.JPanel();
        rightEEG = new javax.swing.JPanel();
        tabRegion1 = new javax.swing.JPanel();
        tabRegion2 = new javax.swing.JPanel();
        tabRegion3 = new javax.swing.JPanel();
        tabRegion4 = new javax.swing.JPanel();
        tabRegion5 = new javax.swing.JPanel();
        tabRegion6 = new javax.swing.JPanel();
        tabRegion7 = new javax.swing.JPanel();
        tabRegion8 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        open = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        NIRSTester = new javax.swing.JMenuItem();
        navi = new javax.swing.JMenu();
        back = new javax.swing.JMenuItem();
        forward = new javax.swing.JMenuItem();
        home = new javax.swing.JMenuItem();
        actions = new javax.swing.JMenu();
        start = new javax.swing.JMenuItem();
        stop = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Multimodal Neural Monitoring");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        tabBrain.setLayout(new javax.swing.BoxLayout(tabBrain, javax.swing.BoxLayout.LINE_AXIS));

        leftMain.setBorder(BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout leftMainLayout = new javax.swing.GroupLayout(leftMain);
        leftMain.setLayout(leftMainLayout);
        leftMainLayout.setHorizontalGroup(
            leftMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );
        leftMainLayout.setVerticalGroup(
            leftMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        tabBrain.add(leftMain);

        rightMain.setLayout(new java.awt.GridBagLayout());
        rightMain.setBorder(BorderFactory.createTitledBorder(""));
        rightMain.setBackground(Color.white);
        setResizable(false)

        cont.implementRegionImages(rightMain, jTabbedPane1);

        tabBrain.add(rightMain);

        jTabbedPane1.addTab("Main", tabBrain);

        tabEEG.setLayout(new java.awt.GridBagLayout());

        leftEEG.setBorder(BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout leftEEGLayout = new javax.swing.GroupLayout(leftEEG);
        leftEEG.setLayout(leftEEGLayout);
        leftEEGLayout.setHorizontalGroup(
            leftEEGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );
        leftEEGLayout.setVerticalGroup(
            leftEEGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        tabEEG.add(leftEEG, new java.awt.GridBagConstraints());

        rightEEG.setLayout(new java.awt.GridLayout(4, 2));
        rightEEG.setBorder(BorderFactory.createTitledBorder(""));
        rightEEG.setBackground(Color.WHITE);
        rightEEG.setLayout(new GridBagLayout());
        //GridBagConstraints GBC = new GridBagConstraints();
        
        try {
            BufferedImage eeg = ImageIO.read(new File("src/images/updated_eeg_map.png"));

            ArrayList<BufferedImage> abiRegions = new ArrayList<BufferedImage>();
            ArrayList<ImageIcon> aiRegions = new ArrayList<ImageIcon>();
            ArrayList<JLabel> ajlRegions = new ArrayList<JLabel>();
            ImageMap imageSlice = new ImageMap(eeg, abiRegions, aiRegions, ajlRegions);
            for(int i = 0; i < ajlRegions.size(); i++) {
                rightEEG.add(ajlRegions.get(i));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        tabEEG.add(rightEEG, new java.awt.GridBagConstraints());

        jTabbedPane1.addTab("EEG", tabEEG);

        tabRegion1.setLayout(new java.awt.GridLayout(4, 2));

        GraphModel model = new DefaultGraphModel();
        try{
            tabRegion1.add(new SeriesChartPane(model));
            tabRegion1.add(new SeriesChartPane(model));
            tabRegion1.add(new SeriesChartPane(model));
            tabRegion1.add(new SeriesChartPane(model));
            tabRegion1.add(new SeriesChartPane(model));
            tabRegion1.add(new SeriesChartPane(model));
            tabRegion1.add(new SeriesChartPane(model));
            tabRegion1.add(new SeriesChartPane(model));
        }
        catch (Exception e) {
            System.out.println("Unable to populate tabRegion with graphs");
        }

        jTabbedPane1.addTab("NIRS 1", tabRegion1);

        javax.swing.GroupLayout tabRegion2Layout = new javax.swing.GroupLayout(tabRegion2);
        tabRegion2.setLayout(tabRegion2Layout);
        tabRegion2Layout.setHorizontalGroup(
            tabRegion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        tabRegion2Layout.setVerticalGroup(
            tabRegion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NIRS 2", tabRegion2);

        javax.swing.GroupLayout tabRegion3Layout = new javax.swing.GroupLayout(tabRegion3);
        tabRegion3.setLayout(tabRegion3Layout);
        tabRegion3Layout.setHorizontalGroup(
            tabRegion3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        tabRegion3Layout.setVerticalGroup(
            tabRegion3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NIRS 3", tabRegion3);

        javax.swing.GroupLayout tabRegion4Layout = new javax.swing.GroupLayout(tabRegion4);
        tabRegion4.setLayout(tabRegion4Layout);
        tabRegion4Layout.setHorizontalGroup(
            tabRegion4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        tabRegion4Layout.setVerticalGroup(
            tabRegion4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NIRS 4", tabRegion4);

        javax.swing.GroupLayout tabRegion5Layout = new javax.swing.GroupLayout(tabRegion5);
        tabRegion5.setLayout(tabRegion5Layout);
        tabRegion5Layout.setHorizontalGroup(
            tabRegion5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        tabRegion5Layout.setVerticalGroup(
            tabRegion5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NIRS 5", tabRegion5);

        javax.swing.GroupLayout tabRegion6Layout = new javax.swing.GroupLayout(tabRegion6);
        tabRegion6.setLayout(tabRegion6Layout);
        tabRegion6Layout.setHorizontalGroup(
            tabRegion6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        tabRegion6Layout.setVerticalGroup(
            tabRegion6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NIRS 6", tabRegion6);

        javax.swing.GroupLayout tabRegion7Layout = new javax.swing.GroupLayout(tabRegion7);
        tabRegion7.setLayout(tabRegion7Layout);
        tabRegion7Layout.setHorizontalGroup(
            tabRegion7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        tabRegion7Layout.setVerticalGroup(
            tabRegion7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NIRS 7", tabRegion7);

        javax.swing.GroupLayout tabRegion8Layout = new javax.swing.GroupLayout(tabRegion8);
        tabRegion8.setLayout(tabRegion8Layout);
        tabRegion8Layout.setHorizontalGroup(
            tabRegion8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        tabRegion8Layout.setVerticalGroup(
            tabRegion8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NIRS 8", tabRegion8);

        file.setText("File");

        open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        file.add(open);

        save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save.setText("Save As ...");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        file.add(save);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        file.add(exit);

        jMenuBar1.add(file);

        edit.setText("Edit");
        edit.add(NIRSTester);
        
        NIRSTester.setText("Test NIRS Region");
        NIRSTester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    cont.changeBrainRegion();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        jMenuBar1.add(edit);

        navi.setText("Navigate");

        back.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT, java.awt.event.InputEvent.ALT_MASK));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        navi.add(back);

        forward.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.ALT_MASK));
        forward.setText("Forward");
        forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardActionPerformed(evt);
            }
        });
        navi.add(forward);

        home.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        navi.add(home);

        jMenuBar1.add(navi);

        actions.setText("Actions");

        start.setText("Start Recording");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        actions.add(start);

        stop.setText("Stop Recording");
        stop.setEnabled(true);
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        actions.add(stop);

        jMenuBar1.add(actions);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        JFileChooser fcSave = new JFileChooser();
        int rVal = fcSave.showSaveDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            // Save the file to directory
        }
    }//GEN-LAST:event_saveActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        if(stop.isEnabled())
            cont.stopRecording();
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        int index = jTabbedPane1.getSelectedIndex() - 1;
        if(index == - 1)
            jTabbedPane1.setSelectedIndex(Global.MAX_TABS - 1);
        else
            jTabbedPane1.setSelectedIndex(index);
    }//GEN-LAST:event_backActionPerformed

    private void forwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardActionPerformed
        // TODO add your handling code here:
        int index = jTabbedPane1.getSelectedIndex() + 1;
        if(index >= Global.MAX_TABS)
            jTabbedPane1.setSelectedIndex(0);
        else
            jTabbedPane1.setSelectedIndex(index);
    }//GEN-LAST:event_forwardActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_homeActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
        stop.setEnabled(true);
        start.setEnabled(false);
    }//GEN-LAST:event_startActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        // TODO add your handling code here:
        if(cont.stopRecording()) {
            stop.setEnabled(false);
            start.setEnabled(true);
        }
    }//GEN-LAST:event_stopActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(stop.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Closing open patient file...");
            cont.stopRecording();
        }
    }//GEN-LAST:event_formWindowClosing

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        // TODO add your handling code here:
        JFileChooser fcOpen = new JFileChooser();
        int rVal = fcOpen.showOpenDialog(this);
        if(rVal == JFileChooser.APPROVE_OPTION) {
            // Open file with the name
        }
    }//GEN-LAST:event_openActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu actions;
    private javax.swing.JMenuItem back;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem NIRSTester;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem forward;
    private javax.swing.JMenuItem home;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel leftEEG;
    private javax.swing.JPanel leftMain;
    private javax.swing.JMenu navi;
    private javax.swing.JMenuItem open;
    private javax.swing.JPanel rightEEG;
    private javax.swing.JPanel rightMain;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem start;
    private javax.swing.JMenuItem stop;
    private javax.swing.JPanel tabBrain;
    private javax.swing.JPanel tabEEG;
    private javax.swing.JPanel tabRegion1;
    private javax.swing.JPanel tabRegion2;
    private javax.swing.JPanel tabRegion3;
    private javax.swing.JPanel tabRegion4;
    private javax.swing.JPanel tabRegion5;
    private javax.swing.JPanel tabRegion6;
    private javax.swing.JPanel tabRegion7;
    private javax.swing.JPanel tabRegion8;
    // End of variables declaration//GEN-END:variables
}
