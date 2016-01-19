package com.snapdeal.sps.intersectISBN.intersection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.monitorjbl.xlsx.StreamingReader;

public class Intersection {

	static int onixIsbnInPC;
	static int onixIsbnNotInPC;
	
	static FileWriter fileWriter;

	public static void writeToXLSX(Set<String> onixSet, Set<String> pcSet,
			String path) {

		int onixIsbnInPCitr = 0;
		int onixIsbnNotInPCitr = 0;
		int onixIsbnInPCFileCount = 1;
		int onixIsbnNotInPCFileCount = 1;

		System.out.println("Going to write file:" + path);

		Workbook isbnInPC = new SXSSFWorkbook();
		Workbook isbnNotInPC = new SXSSFWorkbook();
		SXSSFSheet isbnInPCSheet = (SXSSFSheet) isbnInPC
				.createSheet("isbnInPCSheet");
		SXSSFSheet isbnNotInPCSheet = (SXSSFSheet) isbnNotInPC
				.createSheet("isbnNotInPCSheet");

		Cell c;
		Row row;

		try {
			for (String isbn : onixSet) {
				if (onixIsbnInPCitr > 999999) {
					onixIsbnInPCitr = 0;
					FileOutputStream fileOut = new FileOutputStream(path
							+ "PCIsbnInOnix_" + (onixIsbnInPCFileCount++)
							+ ".xlsx");
					isbnInPC.write(fileOut);
					System.out.println("Written successfully " + fileOut);
					fileOut.close();
					isbnInPC.close();
					isbnInPC = new SXSSFWorkbook();
					isbnInPCSheet = (SXSSFSheet) isbnInPC
							.createSheet("isbnInPCSheet");
				}
				if (onixIsbnNotInPCitr > 999999) {
					onixIsbnNotInPCitr = 0;
					FileOutputStream fileOut = new FileOutputStream(path
							+ "PCIsbnNotInOnix_" + (onixIsbnNotInPCFileCount++)
							+ ".xlsx");
					isbnNotInPC.write(fileOut);
					System.out.println("Written successfully " + fileOut);
					fileOut.close();
					isbnNotInPC.close();
					isbnNotInPC = new SXSSFWorkbook();
					isbnNotInPCSheet = (SXSSFSheet) isbnNotInPC
							.createSheet("isbnNotInPCSheet");
				}
				if (pcSet.contains(isbn)) {
					row = isbnInPCSheet.createRow(onixIsbnInPCitr++);
					onixIsbnInPC++;
					c = row.createCell(0);
					c.setCellValue(isbn);
					// System.out.print(".");
				} else {
					row = isbnNotInPCSheet.createRow(onixIsbnNotInPCitr++);
					onixIsbnNotInPC++;
					c = row.createCell(0);
					c.setCellValue(isbn);
					// System.out.print("-");
				}
				// System.out.println("onixIsbnInPC:" + onixIsbnInPC);
				// System.out.println("onixIsbnNotInPC" + onixIsbnNotInPC);
			}

			FileOutputStream fileOut = new FileOutputStream(path
					+ "PCIsbnInOnix_" + (onixIsbnInPCFileCount++) + ".xlsx");
			isbnInPC.write(fileOut);
			fileOut.close();
			isbnInPC.close();
			System.out.println("Written successfully.");

			fileOut = new FileOutputStream(path + "PCIsbnNotInOnix_"
					+ (onixIsbnNotInPCFileCount++) + ".xlsx");
			isbnNotInPC.write(fileOut);
			fileOut.close();
			isbnNotInPC.close();
			System.out.println("Written successfully.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<SampleData> readSampleOutput(File file) {
		ArrayList<SampleData> sd = new ArrayList<SampleData>();
		try {
			System.out.println("Going to read file:" + file);

			/*
			 * InputStream is = new FileInputStream(myFile); StreamingReader
			 * reader = StreamingReader.builder()
			 * .rowCacheSize(1000).bufferSize(4096).sheetIndex(0).read(is);
			 */

			OPCPackage pkg = OPCPackage.open(file);
			XSSFWorkbook workbook = new XSSFWorkbook(pkg);
			XSSFSheet sheet = workbook.getSheetAt(0);

			/*
			 * Workbook workbook = new SXSSFWorkbook(new XSSFWorkbook(new
			 * FileInputStream(file))); Sheet sheet = workbook.getSheetAt(0);
			 */

			Iterator<Row> rowIterator = sheet.iterator();
			// int count = mySheet.getPhysicalNumberOfRows();
			System.out.println("PhysicalNumberOfRows:"
					+ sheet.getPhysicalNumberOfRows());
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				for (int i = 0; i < 20; i++) {
					if (row.getCell(i) == null)
						row.createCell(i);
				}

				int cellIndex = 0;
				sd.add(new SampleData(row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue(), row.getCell(cellIndex++)
						.getStringCellValue()));
			}

			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sd;
	}

	
	
	public static int readXLSX(File myFile, Set<String> set, int index) {

		int count = 0;
		
		try {
			System.out.println("Going to read file:" + myFile);

			/*
			 * InputStream is = new FileInputStream(myFile); StreamingReader
			 * reader = StreamingReader.builder()
			 * .rowCacheSize(1000).bufferSize(4096).sheetIndex(0).read(is);
			 */

			OPCPackage pkg = OPCPackage.open(myFile);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			count = mySheet.getPhysicalNumberOfRows();
			System.out.println("PhysicalNumberOfRows:" + count);
			rowIterator.next();
			
			

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				/*
				 * for (Row row : reader) { if (count == 0) { continue; }
				 */
				Cell cell = row.getCell(index);
				// cell.setCellType(Cell.CELL_TYPE_STRING);
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					/*
					 * System.out.println("CELL_TYPE_STRING:"+cell.
					 * getStringCellValue().replaceAll( "[- .]", ""));
					 */

					String cellValue = cell.getStringCellValue()
							.replaceAll("[- .]", "").trim();
					System.out.println(cellValue);
					fileWriter.append(cellValue+"\n");
					if (set.add(cellValue)) {

					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					count++;
					System.out.println("CELL_TYPE_NUMERIC:"
							+ String.valueOf(cell.getNumericCellValue())
									.replaceAll("[- ]", ""));
					if (set.add(String.valueOf(cell.getNumericCellValue())
							.replaceAll("[- ]", "").trim())) {
					}
					break;
				default:
					System.out.println("Fell in default block.");

				}
			}

			myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String args[]) {

		String onixDirectoryPath = "/home/divya/Music/bookListing";
		String pcDirectoryPath = "/home/divya/Music/PC2";

		Set<String> onixIsbnSet = new HashSet<String>();
		Set<String> pcIsbnSet = new HashSet<String>();

		int onixTotalRecord = 0;
		int pcTotalRecord = 0;
		
		try {
			fileWriter = new FileWriter(new File("/home/divya/Documents/toBePut.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File dir = new File(onixDirectoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				onixTotalRecord += readXLSX(child, onixIsbnSet, 4);
				System.out.println("onixTotalRecord:" + onixTotalRecord);
			}
		}

		/*dir = new File(pcDirectoryPath);
		directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				pcTotalRecord += readXLSX(child, pcIsbnSet, 0);
				System.out.println("pcTotalRecord:" + pcTotalRecord);
			}
		}*/

		//writeToXLSX(onixIsbnSet, pcIsbnSet, "/home/divya/Music/intersect/");

		try {
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("total no of records in Onix dataset:"
				+ onixTotalRecord);

		System.out.println("Unique no of isbns in Onix dataset:"
				+ onixIsbnSet.size());

		System.out
				.println("total no of records in PC dataset:" + pcTotalRecord);

		System.out.println("Unique no of isbns in PC dataset:"
				+ pcIsbnSet.size());

		System.out.println("Onix isbn in PC:" + onixIsbnInPC);
		System.out.println("Onix isbn not in PC:" + onixIsbnNotInPC);

		System.out.println("==============Completed================");

	}
}
