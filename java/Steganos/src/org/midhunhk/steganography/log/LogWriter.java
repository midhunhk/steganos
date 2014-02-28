package org.midhunhk.steganography.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import org.midhunhk.steganography.utils.AppUtils;

/**
 * An implementation for LogWriter
 * 
 * @author Midhun
 * 
 */
public class LogWriter implements ILogWriter {

	private File				logFile;
	private FileOutputStream	logFileStream;
	private static ILogWriter	instance;
	private static String		CLASS_NAME	= LogWriter.class.getName();

	/**
	 * This class will be following Singleton pattern
	 * 
	 * @param logFileName
	 */
	private LogWriter(String logFileName) {
		String sBaseFolder = AppUtils.getResourceFolder("logs");
		logFile = new File(sBaseFolder + logFileName);
		try {
			logFile.createNewFile();
			logFileStream = new FileOutputStream(logFile);
			writeln(CLASS_NAME, "logger is ready");
		} catch (FileNotFoundException e) {
			System.err.println("LogWriter-EXCEPTION1 : " + e.getMessage());
		} catch (Exception e) {
			System.err.println("LogWriter-EXCEPTION : " + e.getMessage());
		}

	}

	/**
	 * Returns the single instance of this logger for this session
	 * 
	 * @return
	 */
	public ILogWriter getInstance() {
		if (instance == null) {
			instance = new LogWriter("");
		}
		return instance;
	}

	@Override
	public String getLogName() {
		if (logFile != null) {
			return logFile.getName();
		}
		return null;
	}

	@Override
	public boolean writeln(String className, String data) {
		return (write(className, data + "\r\n"));
	}

	public boolean write(String className, String data) {
		try {
			String logMessage = className + ":" + data;
			String currTime = getTimeString();

			logFileStream.write(currTime.getBytes());
			logFileStream.write(logMessage.getBytes(), 0, logMessage.length());
			logFileStream.flush();

			return true;
		} catch (Exception e) {
			System.err.println("LogWriter-EXCEPTION : " + e.getMessage());
		}
		return false;
	}

	private String getTimeString() {
		Date cd = Calendar.getInstance().getTime();
		String properTime = new String(cd.toString());
		return "[" + properTime + "] ";
	}

}