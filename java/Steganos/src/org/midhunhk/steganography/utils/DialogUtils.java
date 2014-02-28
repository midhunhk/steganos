package org.midhunhk.steganography.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogUtils {

	private Component	parent;

	public DialogUtils(Component parent) {
		this.parent = parent;
	}

	public void showMessage(String title, String message) {

	}

	public void showError(String title, String message) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
	}
}
