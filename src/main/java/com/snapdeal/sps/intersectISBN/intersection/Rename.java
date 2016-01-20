package com.snapdeal.sps.intersectISBN.intersection;

import java.io.File;

public class Rename {

	public static void main(String[] args) {

		File[] dir = new File(
				"/home/divya/brandSponsered/BooksMapping/Downloads/bookssheet/mrg processed 6k")
				.listFiles();

		for (File file : dir) {
			if (file.renameTo(new File(
					"/home/divya/brandSponsered/BooksMapping/Downloads/bookssheet/mrg/"
							+ file.getName().substring(0,
									file.getName().lastIndexOf("."))))) {
				System.out.println("renamed");
			} else {
				System.out.println("Error");
			}
		}

	}
}
