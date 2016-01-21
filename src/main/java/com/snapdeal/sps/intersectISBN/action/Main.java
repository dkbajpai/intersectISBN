package com.snapdeal.sps.intersectISBN.action;

import com.snapdeal.sps.intersectISBN.classes.Initializer;




public class Main {

	public static void main(String args[]) {

		Initializer.initialize();
		
		ProcessEngine.readAndProcessInputText();
		
		

	}
}
