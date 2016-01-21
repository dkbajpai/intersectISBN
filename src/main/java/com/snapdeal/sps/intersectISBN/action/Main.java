package com.snapdeal.sps.intersectISBN.action;

<<<<<<< HEAD
import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.utils.FileUploadFTP;
import com.snapdeal.sps.intersectISBN.utils.GeneralUtils;
=======
import com.snapdeal.sps.intersectISBN.classes.Initializer;


>>>>>>> a6941af82e2e09eb03618083cce590c61761e6be


public class Main {

	public static void main(String args[]) {

		Initializer.initialize();
		
<<<<<<< HEAD
		
		//uploading files 
		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.LOCAL_UPLOAD_SHEET_LOCATION),1);
		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.LOCAL_REJECTED_SHEET_LOCATION),2);
		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.LOCAL_BOOKS_SHEET_LOCATION),3);
		
		//ProcessEngine.readAndProcessInputText();
=======
		ProcessEngine.readAndProcessInputText();
>>>>>>> a6941af82e2e09eb03618083cce590c61761e6be
		
		

	}
}
