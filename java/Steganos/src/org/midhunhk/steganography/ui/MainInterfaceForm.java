package org.midhunhk.steganography.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.midhunhk.steganography.core.SteganoInformation;
import org.midhunhk.steganography.core.Steganograph;
import org.midhunhk.steganography.ui.widgets.ImageButton;
import org.midhunhk.steganography.ui.widgets.WidgetFactory;
import org.midhunhk.steganography.utils.DialogUtils;
import org.midhunhk.steganography.utils.SimpleFileFilter;

/**
 * This is a Java Swing form that the user can interact with.
 * 
 * @author admin
 */
public class MainInterfaceForm extends JFrame implements ActionListener {

	private static final long	serialVersionUID		= 5775503582879812597L;
	private JLabel				lblMsg;
	private JLabel				lblSource;
	private JLabel				lblTarget;
	private JFileChooser		fileChooserSource;
	private JFileChooser		fileChooserTarget;
	private JScrollPane			scroller;

	private JButton				btnSelectSource;
	private JButton				btnSelectTarget;

	private ImageButton			imBtnSteg;
	private ImageButton			imBtnDesteg;
	private ImageButton			imBtnClear;
	private ImageButton			imBtnExit;
	private JCheckBox			chkCompress;

	private JTextField			txtSourceFile;
	private JTextField			txtTargetFile;
	private JTextArea			txtMessageArea;

	// Status flags
	private boolean				isSourceOpen;
	private boolean				isTargetOpen;
	private int					defaultCompressionLevel	= 6;

	public static void main(String[] args) {
		// This is the entry point to the application
		new MainInterfaceForm();
	}

	public MainInterfaceForm() {
		// lwBigBrother= lw;

		isSourceOpen = false;
		isTargetOpen = false;

		lblMsg = new JLabel("Message");
		lblSource = new JLabel("Source : ");
		lblTarget = new JLabel("Target : ");

		btnSelectSource = new JButton("Browse");
		btnSelectTarget = new JButton("Browse");

		chkCompress = new JCheckBox("Compress", false);

		txtMessageArea = new JTextArea(1, 1);
		txtMessageArea.setEditable(true);

		getContentPane().add(new JScrollPane(txtMessageArea));
		scroller = new JScrollPane(txtMessageArea);
		scroller.setViewportView(txtMessageArea);

		txtSourceFile = new JTextField();
		txtTargetFile = new JTextField();
		txtTargetFile.setEditable(false);
		txtSourceFile.setEditable(false);

		fileChooserSource = new JFileChooser();
		fileChooserTarget = new JFileChooser();

		fileChooserSource.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooserTarget.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// This should come from WaveAudioSteganoStream
		String[] audios = new String[] { "au", "aiff", "wav" };
		FileFilter fileFilter = new SimpleFileFilter(audios, "Sound (*.aiff, *.au, *.wav)");

		fileChooserSource.addChoosableFileFilter(fileFilter);
		fileChooserTarget.addChoosableFileFilter(fileFilter);

		// Exit Button is placed at the top right
		imBtnExit = WidgetFactory.createImageButton("btn_close_normal.png", "btn_close_hover.png", 380, 5, 50, 50);

		// Other interface buttons
		imBtnSteg = WidgetFactory.createImageButton("btn_steg_normal.png", "btn_steg_hover.png", 25, 90, 73, 32);
		imBtnClear = WidgetFactory.createImageButton("btn_clear_normal.png", "btn_clear_hover.png", 100, 90, 85, 32);
		imBtnDesteg = WidgetFactory.createImageButton("btn_desteg_normal.png", "btn_desteg_hover.png", 180, 90, 75, 32);

		// imBtnSelectSource = new
		// ImageButton("graphics/exported/btnBrowse_norm.png","graphics/exported/btnBrowse_over.png",300,100,100,200);
		// imBtnSelectTarget = new
		// ImageButton("graphics/exported/btnBrowse2_norm.png","graphics/exported/btnBrowse2_over.png",300,130,100,200);

		// set bounds
		lblSource.setBounds(25, 140, 80, 20);
		lblTarget.setBounds(25, 170, 80, 20);
		btnSelectSource.setBounds(300, 140, 100, 20);
		btnSelectTarget.setBounds(300, 170, 100, 20);

		txtSourceFile.setBounds(80, 140, 200, 20);
		txtTargetFile.setBounds(80, 170, 200, 20);

		lblMsg.setBounds(25, 220, 400, 20);
		txtMessageArea.setBounds(25, 240, 385, 180);
		txtMessageArea.setAutoscrolls(true);
		chkCompress.setBounds(300, 200, 100, 20);

		// add the elements to the frame
		getContentPane().add(btnSelectSource);
		getContentPane().add(btnSelectTarget);

		getContentPane().add(imBtnSteg);
		getContentPane().add(imBtnDesteg);
		getContentPane().add(imBtnClear);
		getContentPane().add(imBtnExit);

		getContentPane().add(scroller);
		getContentPane().add(lblMsg);
		getContentPane().add(lblSource);
		getContentPane().add(lblTarget);
		getContentPane().add(txtMessageArea);
		getContentPane().add(chkCompress);
		getContentPane().add(txtSourceFile);
		getContentPane().add(txtTargetFile);

		// add background image for the form
		add(WidgetFactory.createImageBackground("main_background.png"));

		// add action listners for buttons
		btnSelectSource.addActionListener(this);
		btnSelectTarget.addActionListener(this);

		imBtnSteg.addActionListener(this);
		imBtnDesteg.addActionListener(this);
		imBtnClear.addActionListener(this);
		imBtnExit.addActionListener(this);

		// imBtnSelectSource.addActionListener(this);
		// imBtnSelectTarget.addActionListener(this);

		// finally set stage params
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Remove the default bounding box supplied by java
		this.setUndecorated(true);
		this.getContentPane().setLayout(null);
		this.setTitle("Steganography Application");
		this.setSize(435, 450);
		this.setLocation(300, 150);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		// select source
		if (ae.getSource() == btnSelectSource) {
			try {
				// show dialogue box for choosing new file.
				// strEncrKey=JOptionPane

				String strFilename;
				DialogUtils dialogUtils = new DialogUtils(this);

				int r = fileChooserSource.showOpenDialog(this);
				File fEncFile = fileChooserSource.getSelectedFile();

				if (r == JFileChooser.CANCEL_OPTION) {
					// Do nothing
					;
				} else {
					strFilename = fEncFile.getName();
					if (!(strFilename.endsWith(".wav"))) {
						dialogUtils.showError("Wrong File type", "Please Select Only Wave files (*.wav)");
					} else {
						// ok
						// txtFilepath.setText(strFilename);
						isSourceOpen = true;
						String selectedFilePath = fileChooserSource.getSelectedFile().getAbsolutePath();
						txtSourceFile.setText(selectedFilePath);
						// lwBigBrother.writeLine("User has selected a Source file : "+selectedFilePath);
					}
				}
			} catch (Exception e) {
				// lwBigBrother.writeLine("stegDesteg - Exception : " +e.getMessage());
			}
		}
		// select target
		else if (ae.getSource() == btnSelectTarget) {
			try {
				// show dialogue box for choosing new file.
				int r = fileChooserTarget.showOpenDialog(this);
				File fEncFile = fileChooserTarget.getSelectedFile();
				String strFoldername;
				if (r == JFileChooser.CANCEL_OPTION) {
					// do nothing
					;
				} else {
					strFoldername = fEncFile.getPath();
					if (strFoldername.length() < 1)
						JOptionPane.showMessageDialog(this, "Please Select an output location", "No Folder chsosen",
								JOptionPane.ERROR_MESSAGE);
					else {
						// ok
						// txtFilepath.setText(strFilename);
						isTargetOpen = true;
						String selectedFilePath = fileChooserTarget.getSelectedFile().getAbsolutePath();
						if (!selectedFilePath.endsWith(".wav"))
							selectedFilePath += ".wav";
						txtTargetFile.setText(selectedFilePath);
						// lwBigBrother.writeLine("User has selected an output file : "+selectedFilePath);
					}
				}
			} catch (Exception e) {
				// lwBigBrother.writeLine("stegDesteg - Exception : " +e.getMessage());
			}
		}
		// clear flds
		else if (ae.getSource() == imBtnClear) {
			txtMessageArea.setText("");
			txtSourceFile.setText("");
			txtTargetFile.setText("");
			isSourceOpen = false;
			isTargetOpen = false;
		} else if (ae.getSource() == imBtnExit) {
			// exit the program
			this.dispose();
		} else if (ae.getSource() == imBtnSteg) {
			if (isSourceOpen == true && isTargetOpen == true) {
				// StegCoreApp

				// get key and encrypt file
				String targetFilepath, targetFilename, targetFile;
				String encrKey;
				// show key dialog and steg
				encrKey = JOptionPane.showInputDialog("Enter Encryption key : ");
				if (encrKey.length() < 8) {
					// we should add some padding
					JOptionPane.showMessageDialog(null, "Password length should be atleast 8 characters",
							"Invalid Input", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// set correct compression level
				int compressionLevel = (chkCompress.isSelected()) ? defaultCompressionLevel : 0;

				// get output file path / name
				targetFilepath = txtTargetFile.getText();
				targetFilename = targetFilepath.substring(targetFilepath.lastIndexOf('\\') + 1);
				if (targetFilename.length() > 0) {
					if (!targetFilename.endsWith(".wav")){
						targetFilename += ".wav";
					}

					targetFile = targetFilepath.substring(0, targetFilepath.lastIndexOf('\\')) + "\\";
					targetFile += targetFilename;

					//
					// the appCoreSteg Process here
					//
					Steganograph encrObj = new Steganograph();
					encrObj.embedMessage(new File(txtSourceFile.getText()), new File(txtTargetFile.getText()),
							txtMessageArea.getText(), compressionLevel, encrKey);
					JOptionPane.showMessageDialog(this, "Output file \n\"" + targetFile + " \"\ncreated successfully",
							"Success", JOptionPane.INFORMATION_MESSAGE);
					// lwBigBrother.writeLine("Output file " + targetFile + " created successfully.");
				}
			} else {
				// no file chosen
				String sMissingInfo;
				if (isSourceOpen == true && isTargetOpen == false)
					sMissingInfo = " Target File";
				else if (isSourceOpen == false && isTargetOpen == true)
					sMissingInfo = " Source File";
				else
					sMissingInfo = "Target & Source Files";

				JOptionPane.showMessageDialog(this, "Please Select the" + sMissingInfo + " (*.wav)", "No File Choosen",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (ae.getSource() == imBtnDesteg) {
			if (isSourceOpen) {
				// DestegAppCore
				SteganoInformation steg = new SteganoInformation(new File(txtSourceFile.getText()));
				String decrKey = JOptionPane.showInputDialog("Enter Decryption key : ");
				String message;

				if (decrKey.length() < 8) {
					// validate for minimum key length
					JOptionPane.showMessageDialog(null, "Password length should be atleast 8 characters",
							"Invalid Input", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// start key operation
				Steganograph decrObj = new Steganograph();
				message = decrObj.retrieveMessage(steg, new String(decrKey));

				if (message != null && !message.equals("#FAILED#")) {
					// JOptionPane.showMessageDialog(null, message, "DEBUG", JOptionPane.WARNING_MESSAGE);
					txtMessageArea.setText(message);
					// lwBigBrother.writeLine("Successfully extracted encrypted data.");
				} else {
					message = Steganograph.getMessage();
					if (message != null && message.equals("Incorrent Password"))
						JOptionPane.showMessageDialog(null,
								"Unfortunately it seems that the password is incorrect.",
								"Invalid password!", JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Error!\n" + Steganograph.getMessage(), "Error!",
								JOptionPane.ERROR_MESSAGE);
				}

			} else {
				// no file chosen
				JOptionPane.showMessageDialog(this, "Please Select a Wave file (*.wav)", "No File Choosen",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}