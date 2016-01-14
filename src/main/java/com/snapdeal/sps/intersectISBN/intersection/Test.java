package com.snapdeal.sps.intersectISBN.intersection;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;


public class Test {

	public static void main(String args[]) {

		Workbook workBook;
		InputStream is;
		//StreamingReader reader ;
				
		try {
			
			is = new FileInputStream(new File("/home/divya/Music/IntersectResult.xlsx"));
			StreamingReader reader = StreamingReader.builder()
					.rowCacheSize(100)
					.bufferSize(4096) 
					.sheetIndex(0)
					.read(is);
			
			for (Row r : reader) {
				  for (Cell c : r) {
				    System.out.println(c.getStringCellValue());
				  }
				}
			
			/*new SXSSFWorkbook
			workBook = (new XSSFWorkbook(new File(
					"/home/divya/Music/item_2_books_set2_deduped_n-20160108_merged_data.xlsx")));
					
			Sheet sheet = null;
			sheet = (XSSFSheet) workBook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			System.out.println("PhysicalNumberOfRows:"
					+ sheet.getPhysicalNumberOfRows());

			rowIterator.next();
			int cellIndex = 0;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				row.getCell(cellIndex).getStringCellValue();
			}*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
