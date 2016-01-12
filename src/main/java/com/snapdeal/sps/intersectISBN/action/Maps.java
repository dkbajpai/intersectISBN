package com.snapdeal.sps.intersectISBN.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Maps {

	static List<SampleData> list = new ArrayList<SampleData>();
	static Map<String, String> mapCat = new HashMap<String, String>();

	public static int readXLSX(File myFile) {

		int count = 0;
		try {
			System.out.println("Going to read file:" + myFile);

			OPCPackage pkg = OPCPackage.open(myFile);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			count = mySheet.getPhysicalNumberOfRows();
			System.out.println("PhysicalNumberOfRows:" + count);
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				int cellItr = 0;
				/*Iterator<Cell> itr = row.iterator();
				*/for (int i = 0; i < 20; i++) {
					if (row.getCell(i) == null)
						row.createCell(i);
				}
				
				System.out.println(":::::"+row.getCell(0).toString()+":::"+row.getCell(1).toString());

				list.add(new SampleData(row.getCell(cellItr++).getStringCellValue(), row
						.getCell(cellItr++).getStringCellValue(), row.getCell(cellItr++)
						.getStringCellValue(), row.getCell(cellItr++).getStringCellValue(), row
						.getCell(cellItr++).getStringCellValue(), row.getCell(cellItr++)
						.getStringCellValue(), row.getCell(cellItr++).getStringCellValue(), row
						.getCell(cellItr++).toString(), row.getCell(cellItr++)
						.getStringCellValue(), row.getCell(cellItr++).getStringCellValue(), row
						.getCell(cellItr++).getStringCellValue()));

				/*list.add(new SampleData(itr.next().toString(), itr.next()
						.toString(), itr.next().toString(), itr.next()
						.toString(), itr.next().toString(), itr.next()
						.toString(), itr.next().toString(), itr.next()
						.toString(), itr.next().toString(), itr.next()
						.toString(), itr.next().toString()));*/
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

	public static void writeSampleFile(List<SampleData> sampleData,
			String path, Map<String, String> mapCat) {

		int currentRow = 0;
		Workbook workbook = new SXSSFWorkbook(1000);
		Row row;
		Sheet sheet = workbook.createSheet("Sheet1");

		ArrayList<String> headers = new ArrayList<String>();
		headers.add("title");
		headers.add("author");
		headers.add("publisher");
		headers.add("isbn10 RecordReference");
		headers.add("isbn13 IdValue");
		headers.add("text");
		headers.add("numberOfPages");
		headers.add("publicationDate");
		headers.add("BIC Main Subject");
		headers.add("Language");
		headers.add("Binding");
		headers.add("Parent Category");
		headers.add("Child Category");

		row = sheet.createRow(currentRow++);
		int cellIndex = 0;
		for (String header : headers) {
			Cell cell = row.createCell(cellIndex++);
			cell.setCellValue(header);
		}

		for (SampleData sd : sampleData) {
			cellIndex = 0;

			row = sheet.createRow(currentRow++);

			Cell cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getTitle());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getAuthor());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getPublisher());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getIsbn10RecordReference());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getIsbn13IdValue());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getText());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getNumberOfPages());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(Utils.formatTimeStamp(sd.getPublicationDate()));

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getBicMainSubject());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getLanguage());

			cell = row.createCell(cellIndex++);
			cell.setCellValue(sd.getBinding());

			cell = row.createCell(cellIndex++);
			cell.setCellValue("Books");

			cell = row.createCell(cellIndex++);
			cell.setCellValue(mapCat.get(sd.getBicMainSubject()));

		}

		try {

			FileOutputStream fileOut = new FileOutputStream(path);
			workbook.write(fileOut);

			fileOut.flush();
			fileOut.close();
			workbook.close();
			System.out.println(path + " written");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void readBictoCat(File file) {

		int count = 0;
		try {
			System.out.println("Going to read file:" + file);

			/*
			 * InputStream is = new FileInputStream(myFile); StreamingReader
			 * reader = StreamingReader.builder()
			 * .rowCacheSize(1000).bufferSize(4096).sheetIndex(0).read(is);
			 */

			OPCPackage pkg = OPCPackage.open(file);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			// count = mySheet.getPhysicalNumberOfRows();
			// System.out.println("PhysicalNumberOfRows:" + count);
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				/*
				 * for (Row row : reader) { if (count == 0) { continue; }
				 */
				Cell bms = row.getCell(0);
				Cell subCat = row.getCell(4);
				// cell.setCellType(Cell.CELL_TYPE_STRING);
				mapCat.put(bms.getStringCellValue(),
						subCat.getStringCellValue());
				// System.out.println(bms.getStringCellValue()+"_____"+subCat.getStringCellValue());

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

	public static void main(String args[]) {

		readBictoCat(new File(
				"/home/divya/Music/101201 BIC2.1 Subj only (2).xlsx"));
		System.out.println("Map size:" + mapCat.size());
		

		String directoryPath = "/home/divya/Music/rejected";

		File dir = new File(directoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				readXLSX(child);
			}
		}
		System.out.println("Sixe:"+list.size());
		writeSampleFile(list, "/home/divya/Music/rejected/aaaaaa.xlsx", mapCat);
	}
}
