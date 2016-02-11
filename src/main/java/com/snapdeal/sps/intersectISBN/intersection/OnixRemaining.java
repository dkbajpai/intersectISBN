package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.snapdeal.sps.intersectISBN.dto.DimensionsDTO;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.utils.GeneralUtils;

public class OnixRemaining {
	
	/*public static String getValid(String val) {
		
		
	}*/

	public static void main(String args[]) throws Exception {

		InputStream is = new FileInputStream(
				"/home/divya/Documents/input/GARDBIB.TXT");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		FileFields fileFields = new FileFields();
		int nameCount = 1;
		FileWriter writer = new FileWriter(new File(
				"/home/divya/Documents/input1/Books_input_"+nameCount+".csv"));
		String header = "ISBN13,ISBN10,Title,PublicationDate,Binding,Language,Authors,"
				+ "Publisher,Description,NumberOfPages,CategoryCode,Weight,Height,Breadth,Length\n";
		int count = 0;
		
		writer.append(header);
		String row;
		try {

			while ((row = reader.readLine()) != null) {
				if (row.contains("**END") || row.equalsIgnoreCase("**")) {
					count++;
					//System.out.println(count);
					String toWrite;
					try{
					toWrite ="\""+ fileFields.getIsbn13() + "\","+
							"\""+ fileFields.getIsbn10() + "\","+
							"\""+ fileFields.getTitle() + "\","+
							"\""+ fileFields.getPublicationDate() + "\","+
							"\""+ fileFields.getBinding() + "\","+
							"\""+ fileFields.getLanguage() + "\","+
							"\""+ fileFields.getAuthors() + "\","+
							"\""+ fileFields.getPublisher() + "\","+
							"\""+ fileFields.getDescription() + "\","+
							"\""+ fileFields.getNumberOfPages() + "\","+
							"\""+ fileFields.getCategoryCode() + "\","+
							"\""+ fileFields.getWeight() + "\","+
							"\""+ fileFields.getHeight() + "\","+
							"\""+ fileFields.getBreadth() + "\","+
							"\""+ fileFields.getLength() + "\"\n";
					//System.out.println(toWrite);
					writer.append(toWrite);
					} catch (Exception e){
						e.printStackTrace();
					}
					if(count >= 500000) {
						writer.flush();
						writer.close();
						nameCount++;
						count = 0;
						writer = new FileWriter(new File("/home/divya/Documents/input1/Books_input_"+nameCount+".csv"));
						writer.append(header);
					}
					fileFields = new FileFields();

				}

				if (row.startsWith("TI")) {
					fileFields.setTitle(row.replaceFirst("TI ", "").trim());

				} else if (row.startsWith("IB")) {
					fileFields.setIsbn10(row.replaceFirst("IB ", "").trim()
							.trim());
				} else if (row.startsWith("I3")) {

					fileFields.setIsbn13(row.replaceFirst("I3 ", "").trim()
							.toLowerCase());

				} else if (row.startsWith("PD")) {
					fileFields.setPublicationDate(row.replaceFirst("PD ", "")
							.trim());
				} else if (row.startsWith("BI")) {
					fileFields.setBinding(row.replaceFirst("BI ", "").trim());

				} else if (row.startsWith("LA")) {
					fileFields.setLanguage(row.replaceFirst("LA ", "").trim());
				} else if (row.startsWith("AU")) {
					fileFields
							.setAuthors(GeneralUtils.getAuthors(fileFields
									.getAuthors())
									+ row.replaceFirst("AU ", "").trim());
				} else if (row.startsWith("PU")) {
					fileFields.setPublisher(row.replaceFirst("PU ", "").trim());
				} else if (row.startsWith("DE")) {
					fileFields.setDescription(row.replaceFirst("DE ", "")
							.trim());
				} else if (row.startsWith("NP")) {
					fileFields.setNumberOfPages(row.replaceFirst("NP ", "")
							.trim());
				} else if (row.startsWith("BC")) {
					fileFields.setCategoryCode(row.replaceFirst("BC ", "")
							.trim());
				} else if (row.startsWith("WE")) {
					fileFields.setWeight(row.replaceFirst("WE ", "").trim());
				} else if (row.startsWith("DI")) {
					DimensionsDTO dimensionsDTO = GeneralUtils
							.getDimensions(row.replaceFirst("DI ", "").trim());
					if (dimensionsDTO != null) {
						fileFields.setLength(dimensionsDTO.getLength());
						fileFields.setBreadth(dimensionsDTO.getBreadth());
						fileFields.setHeight(dimensionsDTO.getHeight());
					}

				}

			}
		} catch (Exception e) {

		}
		writer.flush();
		writer.close();
		reader.close();
	}
}
