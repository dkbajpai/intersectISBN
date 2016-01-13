package com.snapdeal.sps.intersectISBN.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.snapdeal.sps.intersectISBN.dto.DimensionsDTO;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.InputTextDTO;
import com.snapdeal.sps.intersectISBN.enums.XlsxFileHeaders;

public class FileReadUtils {

	public static void readInputTextAndWriteXlsx(File file, String path,
			String fileName, final int BATCHSIZE) {

		try {
			List<FileFields> fileFieldList = new ArrayList<FileFields>();
			InputTextDTO inputTextDTO = new InputTextDTO();
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String row;
			FileFields fileFields = new FileFields();
			while ((row = reader.readLine()) != null) {
				if (row.contains("**END") || row.equalsIgnoreCase("**")) {
					inputTextDTO
							.setTotalRecords(inputTextDTO.getTotalRecords() + 1);
					fileFieldList.add(fileFields);
					fileFields = new FileFields();

					if (row.contains("**END"))
						System.out.println(row.replace("**END,", ""));

					if (inputTextDTO.getTotalRecords() % BATCHSIZE == 0) {
						FileWriteUtils.writeFileFieldsXlsx(fileFieldList,
								XlsxFileHeaders.values(), path, fileName +
								inputTextDTO.getTotalRecords() / BATCHSIZE
										+ ".xlsx");
						fileFieldList.clear();
					}
				}

				if (row.startsWith("TI")) {
					fileFields.setTitle(row.replaceFirst("TI ", ""));

				} else if (row.startsWith("IB")) {
					fileFields.setIsbn10(row.replaceFirst("IB ", ""));
				} else if (row.startsWith("I3")) {
					fileFields.setIsbn13(row.replaceFirst("I3 ", ""));
				} else if (row.startsWith("PD")) {
					fileFields.setPublicationDate(row.replaceFirst("PD ", ""));
				} else if (row.startsWith("BI")) {
					fileFields.setBinding(row.replaceFirst("BI ", ""));
				} else if (row.startsWith("LA")) {
					fileFields.setLanguage(row.replaceFirst("LA ", ""));
				} else if (row.startsWith("AU")) {
					fileFields.setAuthors(GeneralUtils.getAuthors(fileFields
							.getAuthors()) + row.replaceFirst("AU ", ""));
				} else if (row.startsWith("PU")) {
					fileFields.setPublisher(row.replaceFirst("PU ", ""));
				} else if (row.startsWith("DE")) {
					fileFields.setDescription(row.replaceFirst("DE ", ""));
				} else if (row.startsWith("NP")) {
					fileFields.setNumberOfPages(row.replaceFirst("NP ", ""));
				} else if (row.startsWith("BC")) {
					fileFields.setCategoryCode(row.replaceFirst("BC ", ""));
				}
				else if(row.startsWith("WE")){
					fileFields.setWeight(row.replaceFirst("WE ", ""));
				}
				else if(row.startsWith("DI")){
					DimensionsDTO dimensionsDTO = GeneralUtils.getDimensions(row.replaceFirst("DI ", ""));
					fileFields.setLength(dimensionsDTO.getLength());
					fileFields.setBreadth(dimensionsDTO.getBreadth());
					fileFields.setHieght(dimensionsDTO.getHieght());
				}

			}
			
			FileWriteUtils.writeFileFieldsXlsx(fileFieldList,
					XlsxFileHeaders.values(), path, fileName +
					( 1 + inputTextDTO.getTotalRecords()) / BATCHSIZE
							+ ".xlsx");
			reader.close();
			System.out.println("Completed reading file " + file);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	public static List<FileFields> readParsedXlsx(int sheetIndex, File file) {
//
//		List<FileFields> fileFieldsList = new ArrayList<FileFields>();
//
//		System.out.println("Going to read file:" + file);
//		try {
//			Workbook workBook = new XSSFWorkbook(file);
//			XSSFSheet sheet = null;
//			sheet = (XSSFSheet) workBook.getSheetAt(0);
//			Iterator<Row> rowIterator = sheet.iterator();
//			System.out.println("PhysicalNumberOfRows:"
//					+ sheet.getPhysicalNumberOfRows());
//			rowIterator.next();
//			int cellIndex;
//			while (rowIterator.hasNext()) {
//				cellIndex = 0;
//				Row row = rowIterator.next();
//
//				for (int i = 0; i < 20; i++) {
//					if (row.getCell(i) == null)
//						row.createCell(i);
//				}
//
//				fileFieldsList.add(new FileFields(row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue(), row.getCell(cellIndex++)
//						.getStringCellValue()));
//
//			}
//
//			workBook.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return fileFieldsList;
//	}
}