package com.snapdeal.sps.intersectISBN.action;


import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.utils.FileUploadFTP;
import com.snapdeal.sps.intersectISBN.utils.GeneralUtils;

import com.snapdeal.sps.intersectISBN.classes.Initializer;



public class Main {

	public static void main(String args[]) {

		Initializer.initialize();
		
		
		//uploading files 
		
		//ProcessEngine.readAndProcessInputText();
		ProcessEngine.readAndProcessInputText();

		
		

	}
}
