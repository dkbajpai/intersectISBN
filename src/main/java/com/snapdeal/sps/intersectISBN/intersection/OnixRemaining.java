package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.ResultDTO;
import com.snapdeal.sps.intersectISBN.dto.ProcessedDTO;

public class OnixRemaining {

	public static Set<String> isbns50k = new HashSet<String>();
	public static Set<String> isbns = new HashSet<String>();
	
	private  static void getIsbns50k(File file) {
		try {
			System.out
					.println("Inside  getIsbns50k().\nGoing to read file:"
							+ file);
			OPCPackage pkg = OPCPackage.open(file);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				for(int i = 0; i < 1; i++){
					if(row.getCell(i) == null)
						row.createCell(i);
				}
				Cell binding = row.getCell(0);
				isbns50k.add(binding.getStringCellValue().toLowerCase());
			}
			
			myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean readAndProcessInputText() {
		try {
			File dir = new File(Constants.INPUT_TXT_DIRECTORY_PATH);
			File dirList[] = dir.listFiles();
			for (File file : dirList) {
				readInputTextAndWriteXlsx(file,
					
						Constants.BATCHSIZE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static void readInputTextAndWriteXlsx(File file,
			final int BATCHSIZE) {
		

		try {
			
			ResultDTO inputTextDTO = new ResultDTO();
			InputStream is = new FileInputStream(file);
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String row;
			String isbn10=null;
			String isbn13=null;
			
			while ((row = reader.readLine()) != null) {
			
				if (row.contains("**END") || row.equalsIgnoreCase("**")) {
					// it's a simple counter
					inputTextDTO
							.setTotalRecords(inputTextDTO.getTotalRecords() + 1);
					
					if(!isbns50k.contains(row.replaceFirst("I3 ", ""))) {
						isbns.add(isbn10);
						isbn10=null;
						isbn13=null;
					}

				}
				
				if (row.startsWith("I3")) {
					isbn13 = row.replaceFirst("I3 ", "");
				}			
				if (row.startsWith("IB")) {
					isbn10 = row.replaceFirst("IB ", "");
				}	
			}
			reader.close();
			System.out.println("Final summary:" + inputTextDTO);
			System.out
					.println("Completed reading file and written output files."
							+ file);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String args[]) {
		
		getIsbns50k(new File(Constants.ISBNS_50K_PATH));
		
		readAndProcessInputText();
		
		Workbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Sheet1");
		
		
		
		Row row;
		int currentRow = 0;
		int itr =0;
		row = sheet.createRow(currentRow++);
		for(String data: isbns){
			
			if(itr>=12) {
				row = sheet.createRow(currentRow++);
				itr = 0;
			}
			Cell cell = row.createCell(itr++);
			cell.setCellValue(data);
			
		}		
		try {

			FileOutputStream fileOut = new FileOutputStream("/home/divya/Music/tobe.xlsx");
			workbook.write(fileOut);

			fileOut.flush();
			fileOut.close();
			workbook.close();
			System.out.println("Written  successfully.");
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		
	}
}
