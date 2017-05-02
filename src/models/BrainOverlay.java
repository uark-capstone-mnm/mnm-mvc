package models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.PrefixFileFilter;

public class BrainOverlay {

	/** Reads in a region and color and returns the correct colored image for said region<br>
	 * <br>
	 * Probably needs to be rewritten to always have the images loaded into memory so
	 * switching between themd doesn't have to read them into a File[] every time
	 * 
	 * @param region index of the region
	 * @param color integer color to change region
	 * @return
	 */
	public BufferedImage setImage(int region, int color){
		ArrayList<BufferedImage> brainImages = new ArrayList<BufferedImage>(8);
		int i = 0;
		if (color == 1){
			File[] yellowImgs = new File("src/images/").listFiles((FileFilter) new PrefixFileFilter("yellow", IOCase.INSENSITIVE));
			for(File file : yellowImgs) {
				try{
				brainImages.add(ImageIO.read(file));
				}
				catch (IOException ex) {
					System.out.println("Failure to load yellow image " + i);
				}
				i++;
			}
			return brainImages.get(region);
		}
		if (color == 2){
			File[] redImgs = new File("src/images/").listFiles((FileFilter) new PrefixFileFilter("red", IOCase.INSENSITIVE));
			for(File file : redImgs) {
				try{
				brainImages.add(ImageIO.read(file));
				}
				catch (IOException ex) {
					System.out.println("Failure to load red image " + i);
				}
				i++;
			}
			return brainImages.get(region);
		}
		else{
			File[] greenImgs = new File("src/images/").listFiles((FileFilter) new PrefixFileFilter("green", IOCase.INSENSITIVE));
			for(File file : greenImgs) {
				try{
				brainImages.add(ImageIO.read(file));
				}
				catch (IOException ex) {
					System.out.println("Failure to load green image " + i);
				}
				i++;
			}
			return brainImages.get(region);
		}
	}

}
