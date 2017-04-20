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
	
	public double getOxygenThreshold() {
		return prefs.node("options").getDouble("oxygenthreshold", 0.00);
	}
	
	public boolean getDPF() {
		return prefs.node("options").getBoolean("dpf", false);
	}
	
	public int getEpsilon() {
		return prefs.node("options").getInt("epsilon", 0);
	}
	
	public String getValues() {
		return prefs.node("options").get("values", "");
	}
}
