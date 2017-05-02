/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Controller;

/**
 * Initiate the GUI program
 *
 */
public class Main {

    /** Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller cont = new Controller();
        cont.startApplication();
    }
    
}
