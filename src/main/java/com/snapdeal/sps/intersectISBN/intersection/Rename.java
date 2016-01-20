package com.snapdeal.sps.intersectISBN.intersection;

import java.io.File;

public class Rename {

	public static void main(String[] args) {

		File[] dir = new File(
				"/home/divya/Documents/bool1")
				.listFiles();

		for (File file : dir) {
			if (file.renameTo(new File(
					"/home/divya/Documents/bool1/"
							+ file.getName() + ".csv" ))) {
				System.out.println("renamed");
			} else {
				System.out.println("Error");
			}
		}

	}
}
