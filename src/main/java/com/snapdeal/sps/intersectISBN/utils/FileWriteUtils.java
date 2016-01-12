package com.snapdeal.sps.intersectISBN.utils;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.snapdeal.sps.intersectISBN.action.Utils;
import com.snapdeal.sps.intersectISBN.dto.FileFields;

public class FileWriteUtils {

	public static int writeFileFieldsXlsx(List<FileFields> fileFieldsList,
			List<String> headers,
			Map<String, String> subCategoryCodeSubCategoryMap, String path,
			String fileName) {

		System.out.println("Going to write file:" + path);

		Workbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Sheet1");
		int itr = 0;
		Cell c;
		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (String header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header);
			}

			for (FileFields fileFields : fileFieldsList) {
				cellIndex = 0;
				row = sheet.createRow(currentRow++);

				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getTitle());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getAuthors());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getPublisher());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getIsbn10());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getDescription());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getNumberOfPages());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Utils.formatTimeStamp(fileFields
						.getPublicationDate()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getCategoryCode());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getLanguage());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getBinding());

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Books");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(subCategoryCodeSubCategoryMap.get(fileFields
						.getCategoryCode()));

			}
			try {

				FileOutputStream fileOut = new FileOutputStream(path + fileName);
				workbook.write(fileOut);

				fileOut.flush();
				fileOut.close();
				workbook.close();
				System.out.println("Written " + path + " " + fileName
						+ " successfully.");
			} catch (Exception e) {
				e.printStackTrace();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentRow - 1;
	}
}
