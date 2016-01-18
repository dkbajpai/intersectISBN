package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
	
public class FG {

	public static void main(String args[]) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(
						"/home/divya/Documents/forbookreader.csv"))));
		
		String line;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		List<String> list6 = new ArrayList<String>();

		int itr = 0;
		while ((line = reader.readLine()) != null) {
			String[] columns = line.split(",");

			switch (itr % 6) {
			case 0:
				if (Integer.parseInt(columns[2].replaceAll("[' -]", "")) >= 100) {
					list1.add(columns[0].replaceAll("[' -]", ""));
				}
			case 1:
				if (Integer.parseInt(columns[2].replaceAll("[' -]", "")) >= 100) {
					list2.add(columns[0].replaceAll("[' -]", ""));
				}
			case 2:
				if (Integer.parseInt(columns[2].replaceAll("[' -]", "")) >= 100) {
					list3.add(columns[0].replaceAll("[' -]", ""));
				}
			case 3:
				if (Integer.parseInt(columns[2].replaceAll("[' -]", "")) >= 100) {
					list4.add(columns[0].replaceAll("[' -]", ""));
				}
			case 4:
				if (Integer.parseInt(columns[2].replaceAll("[' -]", "")) >= 100) {
					list5.add(columns[0].replaceAll("[' -]", ""));
				}
			case 5:
				if (Integer.parseInt(columns[2].replaceAll("[' -]", "")) >= 100) {
					list6.add(columns[0].replaceAll("[' -]", ""));
				}
			}
			itr++;
		}
			File[] dir = new File("/home/divya/Documents/imageURL").listFiles();
			FileWriter fileWriter = new FileWriter(new File("/home/divya/Documents/ten.csv"));
			
			for (File file : dir) {
				reader = new BufferedReader(new InputStreamReader(
						new FileInputStream(file)));
				
				while ((line = reader.readLine()) != null) {
					String[] cols = line.split(",");	
					if(list1.contains(cols[0])) {
						fileWriter.append(cols[0]+","+cols[1]+"\n");
					}
					else if(list2.contains(cols[0])) {
						fileWriter.append(cols[0]+","+cols[1]+"\n");
					}
					else if(list3.contains(cols[0])) {
						fileWriter.append(cols[0]+","+cols[1]+"\n");
					}
					else if(list4.contains(cols[0])) {
						fileWriter.append(cols[0]+","+cols[1]+"\n");
					}
					else if(list5.contains(cols[0])) {
						fileWriter.append(cols[0]+","+cols[1]+"\n");
					}
					else if(list6.contains(cols[0])) {
						fileWriter.append(cols[0]+","+cols[1]+"\n");
					}
				}
			}
			
			fileWriter.flush();
			fileWriter.close();
			System.out.println("========");
		}
	
}
