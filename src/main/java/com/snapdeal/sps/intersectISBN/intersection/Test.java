package com.snapdeal.sps.intersectISBN.intersection;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Test {

	public static void main(String args[]) {
	
		Workbook workbook;
		Sheet sheet;
		try {
			workbook = new XSSFWorkbook(new File(""));
			sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			
			rowIterator.next();
			Row row;
			Cell cell;
			String isbn;
			
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				cell = row.getCell(0);
				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					isbn = cell.getStringCellValue();
				case Cell.CELL_TYPE_NUMERIC:
					isbn = String.valueOf(cell.getNumericCellValue());		
				}
			}
			
			Runtime.getRuntime().exec("");
			
			
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
