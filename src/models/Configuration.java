package models;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.prefs.Preferences;

import org.ini4j.*;


public class Configuration {
	Ini configFile;
	static Preferences prefs;
	Wini ini;
	
	public Configuration(String config) {
		System.out.println(System.getProperty("user.dir"));
		
		try {
//			Wini ini = new Wini(new File("src/configs/" + config));
			Ini configFile = new Ini(new File("src/configs/" + config) );
			prefs = new IniPreferences(configFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static double getThreshold(String color) {
            if(Objects.equals(color, new String("yellow"))) {
                return prefs.node("options").getDouble("yellowthreshold", 0.00);
            }
            else if(Objects.equals(color, new String("red"))) {
                return prefs.node("options").getDouble("redthreshold", 0.00);
            }
            return 0.00;
	}
        	
	public static double getDPF(int wavelength) {
            // 760 == 0
            if(wavelength == 0) {
                return prefs.node("options").getDouble("dpf_wavelength1", 0.00);
            }
            // 840 == 1
            else if(wavelength == 1) {
                return prefs.node("options").getDouble("dpf_wavelength2", 0.00);
            }
            return 0.00;
	}
	
	public static double getEpsilon(int wavelength, boolean oxy) {
            // oxy (HbO2) == true
            if(oxy) {
                // 760 == 0
                if(wavelength == 0) {
                    return prefs.node("options").getDouble("epsilon_HbO2_wavelength1", 0);
                }
                // 840 == 1
                else if(wavelength == 1) {
                    return prefs.node("options").getDouble("epsilon_Hb02_wavelength2", 0);
                }
            }
            // deoxy (Hb) == false
            else {
                // 760 == 0
                if(wavelength == 0) {
                    return prefs.node("options").getDouble("epsilon_Hb_wavelength1", 0);
                }
                // 840 == 1
                else if(wavelength == 1) {
                    return prefs.node("options").getDouble("epsilon_Hb_wavelength2", 0);
                }
            }
            return 0.00;
	}
	
	public static int getWarningSoundPitch() {
		return prefs.node("sounds").getInt("warningpitch", 0);
	}
	
	public static int getWarningSoundFrequencey(){
		return prefs.node("sounds").getInt("warningfrequency", 0);
	}
	
	public static int getWarningRepeat(){
		return prefs.node("sounds").getInt("warningrepeat", 0);
	}
	
	public static int getWarningPause(){
		return prefs.node("sounds").getInt("warningpause", 0);
	}
	
	public static int getCriticalSoundPitch() {
		return prefs.node("sounds").getInt("criticalpitch", 0);
	}
	
	public static int getCriticalSoundFrequency(){
		return prefs.node("sounds").getInt("criticalfrequency", 0);
	}
	
	public static int getCriticalSoundRepeat(){
		return prefs.node("sounds").getInt("criticalrepeat", 0);
	}
	
	public static int getCriticalPause(){
		return prefs.node("sounds").getInt("criticalpause", 0);
	}
	
	public static void setWarningSoundPitch(int pitch) {
		prefs.node("sounds").putInt("warningpitch", pitch);
	}
	
	public static void setWarningSoundFrequency(int freq){
		prefs.node("sounds").putInt("warningfrequency", freq);
	}
	
	public static void setWarningSoundRepeat(int repeat){
		prefs.node("sounds").putInt("warningrepeat", repeat);
	}
	
	public static void setWaringPause(int pause){
		prefs.node("sounds").putInt("warningpause", pause);
	}
	
	public static void setCriticalSoundPitch(int pitch) {
		prefs.node("sounds").putInt("criticalpitch", pitch);
	}
	
	public static void setCriticalSoundFrequency(int freq){
		prefs.node("sounds").putInt("criticalfrequency", freq);
	}
	
	public static void setCriticalSoundRepeat(int repeat){
		prefs.node("sounds").putInt("criticalrepeat", repeat);
	}
	
	public static void setCriticalPause(int pause){
		prefs.node("sounds").putInt("criticalpause", pause);
	}
}
