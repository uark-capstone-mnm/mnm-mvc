/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import models.PatientFile;
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
}
