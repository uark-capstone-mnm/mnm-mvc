/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import models.Model;
import views.View;

/**
 *
 * @author thy
 */
public class Controller {
    public void startApplication() {
        View view = new View();
        view.setVisible(true);
    }
    
    public String getMessage() {
        try {
            Model model = new Model();
            return model.getData();
        } catch (IOException ex) {
            return "Error";
        }
    }
    
    public boolean writeMessage(String message){
        try{
            Model model = new Model();
            return model.writeData(message);
        } catch (Exception er) {
            return false;
        }
    }
}
