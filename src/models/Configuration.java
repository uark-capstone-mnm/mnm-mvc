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
	
	public double getYellowThreshold() {
		return prefs.node("options").getDouble("yellowthreshold", 0.00);
	}
        
        public double getRedThreshold() {
		return prefs.node("options").getDouble("redthreshold", 0.00);
	}
	
	public double getDPF() {
		return prefs.node("options").getDouble("dpf", 0.00);
	}
	
	public double getEpsilon() {
		return prefs.node("options").getDouble("epsilon", 0);
	}
	
	public String getValues() {
		return prefs.node("options").get("values", "");
	}
}
