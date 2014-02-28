package org.midhunhk.steganography.ui.widgets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Creates an Image button
 * 
 * @author Midhun
 * 
 */
public class ImageButton extends JButton {

	private static final long	serialVersionUID	= 1978986441793095235L;

	public ImageButton(String normalIcon, String hoverIcon, int left, int top, int width, int height) {
		super(new ImageIcon(normalIcon));
		setVisible(true);
		setFocusPainted(false);
		setRolloverEnabled(true);
		setRolloverIcon(new ImageIcon(hoverIcon));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setBounds(left, top, width, height);
	}

	public ImageButton(String normIcon, String overIcon) {
		setIcon(new ImageIcon(normIcon));
		setRolloverIcon(new ImageIcon(overIcon));
	}

}
