/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author thy
 */
public class StatusBar extends JFrame implements ActionListener{
    JLabel lTime;
    SimpleDateFormat sdf = new SimpleDateFormat( "MMM dd yyyy hh:mm::ss" );
    
    public StatusBar(JFrame frame)
    {
        lTime = new JLabel( sdf.format( new GregorianCalendar().getTime() ) );
        lTime.setHorizontalAlignment( JLabel.RIGHT );
        frame.add( lTime, BorderLayout.SOUTH );
        Timer timer = new Timer( 1000, this );
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        lTime.setText( sdf.format( new GregorianCalendar().getTime() ) );
    }
    
}
