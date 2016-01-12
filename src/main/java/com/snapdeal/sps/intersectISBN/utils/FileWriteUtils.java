package com.snapdeal.sps.intersectISBN.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.RejectedDTO;
import com.snapdeal.sps.intersectISBN.enums.AcceptedFileHeaders;
import com.snapdeal.sps.intersectISBN.enums.RejectedFileHeaders;
import com.snapdeal.sps.intersectISBN.enums.XlsxFileHeaders;
import com.snapdeal.sps.intersectISBN.intersection.Utils;

public class FileWriteUtils {

	public static boolean makeDir(String path) {

		File file = new File(path);
		if (!file.exists()) {
			return file.mkdir();
		}
		return true;
	}

	public static int writeFileFieldsXlsx(List<FileFields> fileFieldsList,
			XlsxFileHeaders[] headers, String path, String fileName) {

		System.out
				.println("Inside writeFileFieldsXlsx().\nGoing to write file:"
						+ path + fileName);
		makeDir(path);

		Workbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Sheet1");

		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (XlsxFileHeaders header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header.toString());
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
				cell.setCellValue(GeneralUtils.getValidDescriptionText(fileFields));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getNumberOfPages());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Utils.formatTimeStamp(fileFields
						.getPublicationDate()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getCategoryCode());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLanguage(fileFields.getLanguage()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBinding(fileFields.getBinding()));

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

	public static int writeAcceptedXlsx(List<FileFields> fileFieldsList,
			AcceptedFileHeaders[] headers,
			Map<String, String> subCategoryCodeSubCategoryMap, String path,
			String fileName) {

		System.out.println("Inside writeAcceptedXlsx().\nGoing to write file:"
				+ path + fileName);

		makeDir(path);

		Workbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Sheet1");

		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (AcceptedFileHeaders header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header.toString());
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
				cell.setCellValue(fileFields.getLanguage());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getBinding());

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Books");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidChildCategory(fileFields.getCategoryCode()));

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

	public static int writeRejectedXlsx(List<RejectedDTO> rejectedDTOList,
			RejectedFileHeaders[] headers,
			Map<String, String> subCategoryCodeSubCategoryMap, String path,
			String fileName) {

		System.out.println("Inside writeAcceptedXlsx().\nGoing to write file:"
				+ path + fileName);

		makeDir(path);

		Workbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Sheet1");

		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (RejectedFileHeaders header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header.toString());
			}

			for (RejectedDTO rejectedDTO : rejectedDTOList) {
				cellIndex = 0;
				row = sheet.createRow(currentRow++);

				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getTitle());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getAuthors());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getPublisher());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getIsbn10());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getDescription());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields()
						.getNumberOfPages());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Utils.formatTimeStamp(rejectedDTO
						.getFileFields().getPublicationDate()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getLanguage());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getBinding());

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Books");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidChildCategory(rejectedDTO.getFileFields().getCategoryCode()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getReason());
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