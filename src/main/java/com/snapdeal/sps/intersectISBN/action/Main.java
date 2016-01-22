package com.snapdeal.sps.intersectISBN.action;


import com.snapdeal.sps.intersectISBN.classes.Initializer;


public class Main {

	public static void main(String args[]) {

		Initializer.initialize();
		
		
		//uploading files 
		
		//ProcessEngine.readAndProcessInputText();
		ProcessEngine.readAndProcessInputText();

		ProcessEngine.readAndProcessInputText();
		
		//uploading files 
//		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.WORKING_DIRECTORY + Constants.ACCEPTED_FILES_DIRECTORY),1);
//		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.WORKING_DIRECTORY + Constants.REJECTED_FILES_DIRECTORY),2);
//		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.WORKING_DIRECTORY + Constants.ACCEPTED_FILES_DIRECTORY),3);
		

	}
}
