package com.snapdeal.sps.intersectISBN.utils;

import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;

public class GeneralUtils {

	public static String getAuthors(String author) {
		if (author == null)
			return "";
		else
			return author + ", ";
	}
	
	public static String getValidLanguage(String language) {

		if (language.equals(""))
			return "Unknown";

		else
			return language;

	}

	public static String getValidBinding(String binding) {

		if(DataUtilities.bindingMap.get(binding.toLowerCase()) != null)
			return DataUtilities.bindingMap.get(binding.toLowerCase());

		else
			return "Unknown";
	}
	
	
	public static String ISBN10toISBN13( String ISBN10 ) {
		String ISBN13  = ISBN10;
		ISBN13 = "978" + ISBN13.substring(0,9);
		int d;

		int sum = 0;
		for (int i = 0; i < ISBN13.length(); i++) {
			d = ((i % 2 == 0) ? 1 : 3);
			sum += ((((int) ISBN13.charAt(i)) - 48) * d);
		}
		sum = 10 - (sum % 10);
		ISBN13 += sum;

		return ISBN13;
	}


	//http://www.mrexcel.com/forum/excel-questions/285670-isbn-13-10-conversion-macro.html
	//for isbn13 to isbn10 conversion

	public static String isbn13ToIsbn10( String isbn13){
		String isbn10 = null;
		int checkSum = 0;
		int multiplyingFactor = 10;
		int chechSumDigit;

		isbn13 = isbn13.replace("-", "").replace(" ", "");
		if(isbn13.length() != 13)
			return isbn10;

		isbn10 = isbn13.substring(3,12);


		for(int i = 0; i < isbn10.length(); i++){
			checkSum = checkSum + (Character.getNumericValue(isbn10.charAt(i)) * multiplyingFactor--);
		}


		chechSumDigit = (11 - (checkSum % 11)) % 11;
		if(chechSumDigit == 10)
			isbn10 = isbn10 + "X";

		else
			isbn10 = isbn10 + chechSumDigit;



		return isbn10;
	}
}
