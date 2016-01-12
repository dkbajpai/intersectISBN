package com.snapdeal.sps.intersectISBN.utils;

public class GeneralUtils {

	public static String getAuthors(String author) {
		if (author == null)
			return "";
		else
			return author + ", ";
	}
}
