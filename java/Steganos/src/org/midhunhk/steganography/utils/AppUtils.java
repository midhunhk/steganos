package org.midhunhk.steganography.utils;

public class AppUtils {

	private static final String	FILE_SEPARATOR	= System.getProperty("file.separator");
	private static final String	APP_BASE_PATH	= System.getProperty("user.dir") + FILE_SEPARATOR;

	/**
	 * Returns the base path of the project
	 * 
	 * @return
	 */
	public static String getAppBasePath() {
		return APP_BASE_PATH;
	}

	/**
	 * Returns the folder located at the app base path
	 * 
	 * @param folderName
	 * @return
	 */
	public static String getResourceFolder(String folderName) {
		return getAppBasePath() + folderName + FILE_SEPARATOR;
	}
}
