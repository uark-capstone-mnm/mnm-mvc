package models;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.ini4j.*;


public class Configuration {
	Ini configFile;
	Preferences prefs;
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
	
	public double getThreshold(int color) {
            // yellow == 0
            if(color == 0) {
                return prefs.node("options").getDouble("yellowthreshold", 0.00);
            }
            // red == 1
            else if(color == 1) {
                return prefs.node("options").getDouble("redthreshold", 0.00);
            }
            return 0.00;
	}
        	
	public double getDPF(int wavelength) {
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
	
	public double getEpsilon(int wavelength, boolean oxy) {
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
	
	public String getValues() {
		return prefs.node("options").get("values", "");
	}
}
