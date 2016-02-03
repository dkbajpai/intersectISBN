package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;
import com.snapdeal.sps.intersectISBN.dto.PriceInventoryDTO;

public class Test {

	public static void main(String args[]) throws IOException {

		Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
		
		Set<String> isbns = new HashSet<String>();
		FileWriter writer = new FileWriter(new File("/home/divya/Downloads/ashish/image_inventory_intersection.csv"));
		
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File("/home/divya/Documents/books help excels/totalImages.csv"))));

		while ((line = reader.readLine()) != null) {
			if(line!=null && line.contains(".jpg")) {
			//	isbns.add();
				String isbn = line.split("[.]")[0].trim();
				if(isbnPriceInventoryMap.containsKey(isbn)) {
					writer.append(isbn
							 + "\n");
				}
			}
			
		}
		
		writer.close();
		reader.close();
	}
}
