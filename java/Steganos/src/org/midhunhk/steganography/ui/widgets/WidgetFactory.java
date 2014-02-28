package org.midhunhk.steganography.ui.widgets;

import java.awt.Component;

/**
 * elps to create widget components
 * 
 * @author Midhun
 * 
 */
public class WidgetFactory {

	private static String	APP_BASE_PATH	= System.getProperty("user.dir") + System.getProperty("file.separator");
	private static String	GRAPHICS_PATH	= "graphics" + System.getProperty("file.separator");

	/**
	 * Creates a Background image
	 * 
	 * @param imageName
	 * @return
	 */
	public static Component createImageBackground(String imageName) {
		return new ImageBackground(APP_BASE_PATH + GRAPHICS_PATH + imageName);
	}

	/**
	 * Creates an ImageButton
	 * 
	 * @param normalImage
	 * @param hoverImage
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @return
	 */
	public static ImageButton createImageButton(String normalImage, String hoverImage, int left, int top, int width,
			int height) {
		String graphicsPath = APP_BASE_PATH + GRAPHICS_PATH;
		return new ImageButton(graphicsPath + normalImage, graphicsPath + hoverImage, left, top, width, height);
	}
}
