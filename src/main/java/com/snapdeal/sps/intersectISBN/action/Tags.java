package com.snapdeal.sps.intersectISBN.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tags {

	static int accepted;
	static int rejected;
	static int title;
	static int bicMainSubject;
	static int author;
	static Set<String> bindingSet = new HashSet<String>();
	static Set<String> languageSet = new HashSet<String>();
	static int language;
	static int binding;
	static int publisher;
	static int isbn10;
	static int isbn13;
	static Map<String, String> mapCat = new HashMap<String, String>();

	static int records;
	static int LA;
	static int BI;
	static Set<String> biSet = new HashSet<String>();
	private static SXSSFWorkbook workbook;
	private static final int BATCHSIZE = 50000;

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

	public static void writeSampleFile(List<SampleData> sampleData, String path) {

		int currentRow = 0;
		workbook = new SXSSFWorkbook(1000);
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
			workbook.dispose();
			System.out.println(path + " written");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getaaa(String a) {
		if (a == null)
			return "";
		else
			return a + ", ";
	}

	public static void read(File file) {

		try {
			List<SampleData> sdList = new ArrayList<SampleData>();
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String row;
			SampleData sampleData = new SampleData();
			while ((row = reader.readLine()) != null) {

				if (row.contains("**END") || row.equalsIgnoreCase("**")) {
					records++;
					sdList.add(sampleData);
					sampleData = new SampleData();

					if (row.contains("**END"))
						System.out.println(row.replace("**END,", ""));
					if (records % BATCHSIZE == 0) {
						writeSampleFile(sdList,
								"/home/divya/Music/bookListing/books_"
										+ records / BATCHSIZE + ".xlsx");
						sdList.clear();
					}
				}

				if (row.startsWith("TI")) {
					sampleData.setTitle(row.replaceFirst("TI ", ""));

				} else if (row.startsWith("IB")) {
					sampleData.setIsbn10RecordReference(row.replaceFirst("IB ",
							""));
				} else if (row.startsWith("I3")) {
					sampleData.setIsbn13IdValue(row.replaceFirst("I3 ", ""));
				} else if (row.startsWith("PD")) {
					sampleData.setPublicationDate(row.replaceFirst("PD ", ""));
				} else if (row.startsWith("BI")) {
					sampleData.setBinding(row.replaceFirst("BI ", ""));
				} else if (row.startsWith("LA")) {
					sampleData.setLanguage(row.replaceFirst("LA ", ""));
				} else if (row.startsWith("AU")) {
					sampleData.setAuthor(getaaa(sampleData.getAuthor())
							+ row.replaceFirst("AU ", ""));
				} else if (row.startsWith("PU")) {
					sampleData.setPublisher(row.replaceFirst("PU ", ""));
				} else if (row.startsWith("DE")) {
					sampleData.setText(row.replaceFirst("DE ", ""));
				} else if (row.startsWith("NP")) {
					sampleData.setNumberOfPages(row.replaceFirst("NP ", ""));
				} else if (row.startsWith("BC")) {
					sampleData.setBicMainSubject(row.replaceFirst("BC ", ""));

				}

			}

			writeSampleFile(sdList, "/home/divya/Music/bookListing/books_"
					+ (1 + records / BATCHSIZE) + ".xlsx");
			System.out.println("Completed reading file " + file);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ProcessedSampleData processData(List<SampleData> list) {

		List<SampleData> acceptedList = new ArrayList<SampleData>();
		List<SampleData> rejectedList = new ArrayList<SampleData>();

		for (SampleData data : list) {
			if (data.getAuthor().equals("")
					|| data.getBicMainSubject().equals("")
					|| data.getBinding().equals("")
					|| data.getIsbn10RecordReference().equals("")
					|| data.getIsbn13IdValue().equals("")
					|| data.getLanguage().equals("")
					|| data.getNumberOfPages().equals("")
					|| data.getPublicationDate().equals("")
					|| data.getPublisher().equals("")
					|| data.getText().equals("") || data.getTitle().equals("")) {
				rejectedList.add(data);
			} else {
				acceptedList.add(data);
			}
		}
		return new ProcessedSampleData(acceptedList, rejectedList);
	}

	public static ProcessedSampleData processDataMandatory(
			List<SampleData> list, Set<String> isbns) {

		List<SampleData> acceptedList = new ArrayList<SampleData>();
		List<SampleData> rejectedList = new ArrayList<SampleData>();

		for (SampleData data : list) {

			if (data.getLanguage().equals("")) {
				language++;
			}
			if (!data.getLanguage().equals("")) {
				languageSet.add(data.getLanguage());
			}
			if (data.getBinding().equals("")) {
				binding++;
			}
			if (!data.getBinding().equals("")) {
				bindingSet.add(data.getBinding());
			}
			if (data.getAuthor().equals("")) {
				author++;
			}
			if (data.getPublisher().equals("")) {
				publisher++;
			}
			if (data.getIsbn10RecordReference().equals("")) {
				isbn10++;
			}
			if (data.getIsbn13IdValue().equals("")) {
				isbn13++;
			}
			if (data.getAuthor().equals("")
					|| data.getBicMainSubject().equals("")
					|| data.getBinding().equals("")
					|| data.getIsbn13IdValue().equals("")
					|| data.getLanguage().equals("")
					|| data.getPublisher().equals("")
					|| data.getTitle().equals("")) {
				if (isbns.contains(data.getIsbn13IdValue())) {
					rejectedList.add(data);
				}

			} else {
				if (isbns.contains(data.getIsbn13IdValue())) {
					acceptedList.add(data);
				}
			}

		}
		return new ProcessedSampleData(acceptedList, rejectedList);
	}

	public static String getFileName(String path) {
		return path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));
	}

	public static void writeToXLSX(Set<String> set, String path, int ind) {

		System.out.println("Going to write file:" + path);

		Workbook isbnInPC = new SXSSFWorkbook();
		SXSSFSheet isbnInPCSheet = (SXSSFSheet) isbnInPC.createSheet("Sheet1");

		int itr = 0;
		Cell c;
		Row row;

		try {
			for (String val : set) {
				row = isbnInPCSheet.createRow(itr++);
				c = row.createCell(0);
				c.setCellValue(val);

			}

			FileOutputStream fileOut = new FileOutputStream(path + "book" + ind
					+ ".xlsx");
			isbnInPC.write(fileOut);
			fileOut.close();
			isbnInPC.close();
			System.out.println("Written successfully.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static Set<String> returnISBNS(String path) throws IOException, InvalidFormatException {
		
		Set<String> isbns = new HashSet<String>();
		OPCPackage pkg = OPCPackage.open(new File(path));
		XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIterator = mySheet.iterator();
		int count = mySheet.getPhysicalNumberOfRows();
		System.out.println("PhysicalNumberOfRows:" + count);
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			/*
			 * for (Row row : reader) { if (count == 0) { continue; }
			 */
			Cell cell = row.getCell(0);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:					
				/*System.out.println("CELL_TYPE_STRING:"+cell.getStringCellValue().replaceAll(
						"[- .]", ""));*/
				String cellValue = cell.getStringCellValue()
						.replaceAll("[- .]", "").trim();
				System.out.println(cellValue);
				if (isbns.add(cellValue)) {
					
				}
				break;
			case Cell.CELL_TYPE_NUMERIC:
				count++;
				System.out.println("CELL_TYPE_NUMERIC:"+String.valueOf(
						cell.getNumericCellValue()).replaceAll("[- ]", ""));
				if (isbns.add(String.valueOf(cell.getNumericCellValue())
						.replaceAll("[- ]", "").trim())) {
				}
				break;
			default:
				System.out.println("Fell in default block.");

			}
		}

		myWorkBook.close();
		return isbns;

	
	}

	public static void main(String args[]) throws InvalidFormatException, IOException {

		readBictoCat(new File(
				"/home/divya/Music/101201 BIC2.1 Subj only (2).xlsx"));
		System.out.println("Map size:" + mapCat.size());

		String acceptedPath = "/home/divya/Music/accepted";
		String rejectedPath = "/home/divya/Music/rejected";
		List<SampleData> list = null;
		ProcessedSampleData sampleData;
		String dirPath = "/home/divya/Music/GARDBIB";
		File dir = new File(dirPath);
		File dirList[] = dir.listFiles();
		for (File file : dirList) {
			// read(file);
		}

		System.out.println("BI set is:" + biSet);

		System.out.println("Count is " + records);
		System.out.println("LA Count is " + LA);
		System.out.println("BI Count is " + BI);
		System.out.println("BI Set Count is " + biSet.size());

		dirPath = "/home/divya/Music/bookListing";
		dir = new File(dirPath);
		dirList = dir.listFiles();

		Set<String> isbns = returnISBNS("/home/divya/Music/intersect/PCIsbnInOnix_1.xlsx");
		List<SampleData> ll = new ArrayList<SampleData>();
		
		
		for (File file : dirList) {
			ll.addAll(Main.readSampleOutput(file));
		}
		
		writeSampleFile(ll,"/home/divya/Music/abc/totalIntersect.xlsx");
		
		dirPath = "/home/divya/Music/abc";
		dir = new File(dirPath);
		dirList = dir.listFiles();
 		
		for (File file : dirList) {
			list = Main.readSampleOutput(file);

			sampleData = processDataMandatory(list,isbns);
			accepted += sampleData.getAcceptedList().size();
			rejected += sampleData.getRejectedList().size();
			writeSampleFile(sampleData.getAcceptedList(), acceptedPath + "/"
					+ getFileName(file.toString()) + "_accepted.xlsx");
			writeSampleFile(sampleData.getRejectedList(), rejectedPath + "/"
					+ getFileName(file.toString()) + "_rejected.xlsx");
			System.out.println("accepted:" + accepted);
			System.out.println("rejected:" + rejected);

		}
		
		
		
		/*for (File file : dirList) {
			list = Main.readSampleOutput(file);

			sampleData = processDataMandatory(list,isbns);
			accepted += sampleData.getAcceptedList().size();
			rejected += sampleData.getRejectedList().size();
			writeSampleFile(sampleData.getAcceptedList(), acceptedPath + "/"
					+ getFileName(file.toString()) + "_accepted.xlsx");
			writeSampleFile(sampleData.getRejectedList(), rejectedPath + "/"
					+ getFileName(file.toString()) + "_rejected.xlsx");
			System.out.println("accepted:" + accepted);
			System.out.println("rejected:" + rejected);

		}*/
		System.out.println("title:" + title);
		System.out.println("author:" + author);
		System.out.println("publisher:" + publisher);
		System.out.println("language:" + language);
		System.out.println("binding:" + binding);
		System.out.println("bicMainSubject:" + bicMainSubject);
		System.out.println("isbn10:" + isbn10);
		System.out.println("isbn13:" + isbn13);
		writeToXLSX(languageSet, "/home/divya/Music/res/", 1);
		writeToXLSX(bindingSet, "/home/divya/Music/res/", 2);
	}
}
