/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTabbedPane;
import tools.Global;

/**
 *
 * @author thy
 */
public class mlRegion implements MouseListener {

    JTabbedPane tabbed;
    int region;

    public mlRegion(JTabbedPane tabbed, int region){
            this.tabbed = tabbed;
            this.region = region;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.tabbed.setSelectedIndex(Global.getIndex(region));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
