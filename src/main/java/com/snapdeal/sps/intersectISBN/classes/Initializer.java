package com.snapdeal.sps.intersectISBN.classes;

import java.io.File;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;

public class Initializer {

	public static void initialize(){
		DataUtilities.loadProgramData();
		System.out.println("start");
		createDirectoryStructure();
	}
	
	
	private static void createDirectoryStructure(){
		createDirectory(Constants.WORKING_DIRECTORY);
		createDirectory(Constants.WORKING_DIRECTORY + Constants.ALL_FILES_DIRECTORY);
		createDirectory(Constants.WORKING_DIRECTORY + Constants.ACCEPTED_FILES_DIRECTORY);
		createDirectory(Constants.WORKING_DIRECTORY + Constants.REJECTED_FILES_DIRECTORY);
		
	}
	
	private static void createDirectory(String path){
		File file = new File(path);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Working Directory is created!");
			} else {
				System.out.println("Failed to create directory! Exiting!!");
				System.exit(0);
			}
		}
	}
}
