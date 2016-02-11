package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.snapdeal.sps.intersectISBN.dto.PriceInventoryDTO;

public class Rename {
	
	
	public static Map<String, PriceInventoryDTO> getisbnPriceInventoryMapCSV(
			File file, String delimiter) {
		Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
		try {

			System.out
					.println("Inside  getisbnPriceInventoryMapCSV().\nGoing to read file:"
							+ file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String line = "";

			int count =0;
			while ((line = reader.readLine()) != null) {
				//count ++;
				try {
					String[] columns = line.split(delimiter);
					
						isbnPriceInventoryMap.put(columns[0].trim()
								.toLowerCase(), new PriceInventoryDTO(
								columns[1], columns[2]));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("isbnPriceInventoryMap size : "
				+ isbnPriceInventoryMap.size());
		return isbnPriceInventoryMap;
	}

	public static void main(String[] args) {

		Map<String, PriceInventoryDTO> map = getisbnPriceInventoryMapCSV(new File("/home/divya/Documents/books help excels/4M_data.csv"), "[, \t	]");
		System.out.println(map.size());
	}
}
