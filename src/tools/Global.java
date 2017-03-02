/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author thy
 */
public class Global {
    /*	
	 * Tab 0 - Brain Panel
	 * Tab 1 - EEG Panel
	 * Tab 2 - Region 1
	 * Tab 3 - Region 2
	 * Tab 4 - Region 3
	 * Tab 5 - Region 4
	 * Tab 6 - Region 5
	 * Tab 7 - Region 6
	 * Tab 8 - Region 7
	 * Tab 9 - Region 8
	 */
	
	public static final int MAX_TABS = 10;
	public static final int MAX_REGIONS = 8;
	public static final int EEG_NODES = 64;
	
	public static int getIndex(int index) {
		return index + 2;
	}
       
}
