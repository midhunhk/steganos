package org.midhunhk.steganography.ui.widgets;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Adds an image as background for this component
 * 
 * @author Midhun
 * 
 */
public class ImageBackground extends Component {
	private static final long	serialVersionUID	= -1970696530807535816L;
	private Image				image;

	public ImageBackground() {
		super();
	}

	/**
	 * 
	 * @param imagePath full path for the image
	 */
	public ImageBackground(String imagePath) {
		super();
		try {
			image = ImageIO.read(new File(imagePath));
			setBounds(0, 0, image.getWidth(this), image.getHeight(this));
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void paint(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}
}
