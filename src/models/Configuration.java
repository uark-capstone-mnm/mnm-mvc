package models;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.prefs.Preferences;

import org.ini4j.*;

/**
 * Handles custom configuration files.
 *
 */
public class Configuration {
	Ini configFile;
	static Preferences prefs;
	
	/**
	 * Constructs a Configuration object.
	 */
	public Configuration(String config) {
		
		try {
			Ini configFile = new Ini(new File("src/configs/" + config) );
			prefs = new IniPreferences(configFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets threshold of a color
	 * @param color desired color threshold
	 * @return
	 */
	public static double getThreshold(String color) {
            if(Objects.equals(color, new String("yellow"))) {
                return prefs.node("options").getDouble("yellowthreshold", 0.00);
            }
            else if(Objects.equals(color, new String("red"))) {
                return prefs.node("options").getDouble("redthreshold", 0.00);
            }
            return 0.00;
	}
      
	/**
	 * Gets DPF
	 * @param wavelength dpf wavelength
	 * @return
	 */
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
	
	/**
	 * Gets Epsilon
	 * @param wavelength epsilon wavelength
	 * @param oxy 
	 * @return
	 */
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
	
	/**
	 * Gets warning pitch
	 * @return
	 */
	public static int getWarningSoundPitch() {
		return prefs.node("sounds").getInt("warningpitch", 0);
	}
	
	/**
	 * Gets warning frequency
	 * @return
	 */
	public static int getWarningSoundFrequencey(){
		return prefs.node("sounds").getInt("warningfrequency", 0);
	}
	
	/**
     * Gets how many times warning sound is repeated
     * @return
     */
	public static int getWarningRepeat(){
		return prefs.node("sounds").getInt("warningrepeat", 0);
	}
	
	/**
     * Gets how many times warning sound is paused
     * @return
     */	
	public static int getWarningPause(){
		return prefs.node("sounds").getInt("warningpause", 0);
	}
	
	/**
	 * Gets critical sound pitch
	 * @return
	 */
	public static int getCriticalSoundPitch() {
		return prefs.node("sounds").getInt("criticalpitch", 0);
	}
	
	
	/**
	 * Gets critical frequency
	 * @return
	 */
	public static int getCriticalSoundFrequency(){
		return prefs.node("sounds").getInt("criticalfrequency", 0);
	}
	
	/**
	 * Gets how many times critical sound is repeated
	 * @return
	 */
	public static int getCriticalSoundRepeat(){
		return prefs.node("sounds").getInt("criticalrepeat", 0);
	}
	
	/**
	 * Gets how many time critical sound is paused
	 * @return
	 */
	public static int getCriticalPause(){
		return prefs.node("sounds").getInt("criticalpause", 0);
	}
	
	/**
	 * Sets warning sound pitch
	 * @param pitch
	 */
	public static void setWarningSoundPitch(int pitch) {
		prefs.node("sounds").putInt("warningpitch", pitch);
	}
	
	/**
	 * Sets warning sound frequency
	 * @param freq
	 */
	public static void setWarningSoundFrequency(int freq){
		prefs.node("sounds").putInt("warningfrequency", freq);
	}
	
	/**
	 * Sets how many times warning sounds should be repeated
	 * @param repeat
	 */
	public static void setWarningSoundRepeat(int repeat){
		prefs.node("sounds").putInt("warningrepeat", repeat);
	}
	
	/**
	 * Sets how many times warning sounds should be paused
	 * @param pause
	 */
	public static void setWaringPause(int pause){
		prefs.node("sounds").putInt("warningpause", pause);
	}
	
	/**
	 * Sets critical sound pitch
	 * @param pitch
	 */
	public static void setCriticalSoundPitch(int pitch) {
		prefs.node("sounds").putInt("criticalpitch", pitch);
	}
	
	/**
	 * Sets critical sound frequency
	 * @param freq
	 */
	public static void setCriticalSoundFrequency(int freq){
		prefs.node("sounds").putInt("criticalfrequency", freq);
	}
	
	/**
	 * Sets how many times critical sounds should repeat
	 * @param repeat
	 */
	public static void setCriticalSoundRepeat(int repeat){
		prefs.node("sounds").putInt("criticalrepeat", repeat);
	}
	
	/**
	 * Sets how many times critical sounds should pause
	 * @param pause
	 */
	public static void setCriticalPause(int pause){
		prefs.node("sounds").putInt("criticalpause", pause);
	}
}
