/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Tool to divide main panel brain image into 8 even subimages.
 *
 */
public class ImageMap {

	/**
	 * Constructs an ImageMap object
	 * @param dimg Full image
	 * @param abiRegions ArrayList of subimages of type BufferedImage
	 * @param aiRegions ArrayList of subimages of type ImageIcon
	 * @param ajlRegions ArrayList of subimages of type JLabel
	 */
	public ImageMap(BufferedImage dimg, ArrayList<BufferedImage> abiRegions, ArrayList<ImageIcon> aiRegions, ArrayList<JLabel> ajlRegions) {
		int w = dimg.getWidth()/2;
		int h = dimg.getHeight()/4;
		abiRegions.add(dimg.getSubimage(0, 0, w, h));
		abiRegions.add(dimg.getSubimage(w, 0, w, h));
		abiRegions.add(dimg.getSubimage(0, h, w, h));
		abiRegions.add(dimg.getSubimage(w, h, w, h));
		abiRegions.add(dimg.getSubimage(0, 2 * h, w, h));
		abiRegions.add(dimg.getSubimage(w, 2 * h, w, h));
		abiRegions.add(dimg.getSubimage(0, 3 * h, w, h));
		abiRegions.add(dimg.getSubimage(w, 3 * h, w, h));
		for (BufferedImage region : abiRegions) {
			aiRegions.add(new ImageIcon(region));
		}
		for(ImageIcon region: aiRegions) {
			ajlRegions.add(new JLabel(region));
		}
	}
}
