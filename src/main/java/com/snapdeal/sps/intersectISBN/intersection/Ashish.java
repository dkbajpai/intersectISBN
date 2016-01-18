package com.snapdeal.sps.intersectISBN.intersection;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class Ashish {

	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        File folder = new File("/home/ashish/Documents/workspace-sts-3.6.3.SR1/BooksMapping/Downloads/bookssheet/Book1");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> images = new ArrayList<String>();

            for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
                //System.out.println("File " + listOfFiles[i].getName());
                images.add(listOfFiles[i].getName());
              } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
                
              }
            }
        System.out.println(images.size());
        write(images);
    }
    
    
    
    
    public static void write(ArrayList<String> images) {

		int currentRow = 0;
		int currentCell = 0;
		SXSSFWorkbook workbook1 = new SXSSFWorkbook(1000);
		Row row;
		Sheet sheet = workbook1.createSheet("Sheet1");

		ArrayList<String> headers = new ArrayList<String>();
		headers.add("images");
		
		
		row = sheet.createRow(currentRow++);
		int cellIndex = 0;
		for(String header : headers){
			Cell cell = row.createCell(cellIndex++);
			cell.setCellValue(header);
		}
		
		
		
		
		for (int i = 0; i < images.size(); i++) {
			currentCell = 0;
			row = sheet.createRow(currentRow++);
			
			
			Cell cell = row.createCell(currentCell++);
			cell.setCellValue(images.get(i));
		
		}

		try {

			String filename = "/home/ashish/Desktop/1000images.xlsx";

			FileOutputStream fileOut = new FileOutputStream(
					 filename);
			workbook1.write(fileOut);

			fileOut.flush();
			fileOut.close();
			workbook1.dispose();
			System.out.println(filename + " written");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
