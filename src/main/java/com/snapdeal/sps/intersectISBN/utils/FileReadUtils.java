package com.snapdeal.sps.intersectISBN.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;
import com.snapdeal.sps.intersectISBN.dto.DecisionDTO;
import com.snapdeal.sps.intersectISBN.dto.DimensionsDTO;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.ResultDTO;
import com.snapdeal.sps.intersectISBN.dto.ProcessedDTO;
import com.snapdeal.sps.intersectISBN.dto.RejectedDTO;
import com.snapdeal.sps.intersectISBN.enums.AcceptedFileHeaders;
import com.snapdeal.sps.intersectISBN.enums.RejectedFileHeaders;

public class FileReadUtils {

	public static void readInputTextAndWriteXlsx(File file, String path,
			final int BATCHSIZE) {
		int acceptedItr = 0;
		int rejectedItr = 0;
		ArrayList<FileFields> acceptedRecords = new ArrayList<FileFields>();
		ArrayList<RejectedDTO> rejectedRecords = new ArrayList<RejectedDTO>();
		DecisionDTO decisionDTO;
		try {
			List<FileFields> fileFieldList = new ArrayList<FileFields>();
			ResultDTO resultDTO = new ResultDTO();
			InputStream is = new FileInputStream(file);
			ProcessedDTO processedDTO = new ProcessedDTO();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String row;
			FileFields fileFields = new FileFields();
			while ((row = reader.readLine()) != null) {
				if (row.contains("**END") || row.equalsIgnoreCase("**")) {
					// it's a simple counter
					resultDTO
							.setTotalRecords(resultDTO.getTotalRecords() + 1);

					//if(DataUtilities.isbns50k.contains(fileFields.getIsbn13())) {
					
						decisionDTO = DataValidator.validateFileFieldData(fileFields);
						
						if(decisionDTO.isValid()){
							acceptedRecords.add(fileFields);
							resultDTO.setAccptedRecords(resultDTO.getAccptedRecords() + 1);
							if(acceptedRecords.size() == BATCHSIZE){
								FileWriteUtils.writeXLSXInValidatorFormat(acceptedRecords, AcceptedFileHeaders.values(), DataUtilities.subCategoryCodeSubCategoryMap,Constants.WORKING_DIRECTORY
										+ Constants.ACCEPTED_FILES_DIRECTORY,
								"Accepted_Book_Listing" + (++acceptedItr)
										+ ".xlsx");
								acceptedRecords.clear();
							}
						}
						
						else{
							rejectedRecords.add(new RejectedDTO(fileFields, decisionDTO.getRejectReason()));
							resultDTO.setRejectedRecords(resultDTO.getRejectedRecords() + 1);
							if(rejectedRecords.size() == BATCHSIZE){
								FileWriteUtils.writeRejectedXlsx(rejectedRecords, RejectedFileHeaders.values(), DataUtilities.subCategoryCodeSubCategoryMap, Constants.WORKING_DIRECTORY
											+ Constants.REJECTED_FILES_DIRECTORY,
									"Rejected_Book_Listing" + (++rejectedItr)
											+ ".xlsx");
								rejectedRecords.clear();
							}
						}
					
					
					//}
					
					fileFields = new FileFields();
					if (row.contains("**END"))
						resultDTO.setTotalRecordsInFile(row.replace(
								"**END,", "").trim());
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
				} else if (row.startsWith("WE")) {
					fileFields.setWeight(row.replaceFirst("WE ", ""));
				} else if (row.startsWith("DI")) {
					DimensionsDTO dimensionsDTO = GeneralUtils
							.getDimensions(row.replaceFirst("DI ", ""));
					if (dimensionsDTO != null) {
						fileFields.setLength(dimensionsDTO.getLength());
						fileFields.setBreadth(dimensionsDTO.getBreadth());
						fileFields.setHeight(dimensionsDTO.getHeight());
					}

				}

			}

			if(acceptedRecords.size() > 0){
				FileWriteUtils.writeXLSXInValidatorFormat(acceptedRecords, AcceptedFileHeaders.values(), DataUtilities.subCategoryCodeSubCategoryMap,Constants.WORKING_DIRECTORY
						+ Constants.ACCEPTED_FILES_DIRECTORY,
				"Accepted_Book_Listing" + (++acceptedItr)
						+ ".xlsx");
			}
			

			if(rejectedRecords.size() > 0){
				FileWriteUtils.writeRejectedXlsx(rejectedRecords, RejectedFileHeaders.values(), DataUtilities.subCategoryCodeSubCategoryMap, Constants.WORKING_DIRECTORY
							+ Constants.REJECTED_FILES_DIRECTORY,
					"Rejected_Book_Listing" + (++rejectedItr)
							+ ".xlsx");
			}
			

			
			reader.close();
			System.out.println("Final summary:" + resultDTO);
			System.out
					.println("Completed reading file and written output files."
							+ file);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// public static List<FileFields> readParsedXlsx(int sheetIndex, File file)
	// {
	//
	// List<FileFields> fileFieldsList = new ArrayList<FileFields>();
	//
	// System.out.println("Going to read file:" + file);
	// try {
	// Workbook workBook = new XSSFWorkbook(file);
	// XSSFSheet sheet = null;
	// sheet = (XSSFSheet) workBook.getSheetAt(0);
	// Iterator<Row> rowIterator = sheet.iterator();
	// System.out.println("PhysicalNumberOfRows:"
	// + sheet.getPhysicalNumberOfRows());
	// rowIterator.next();
	// int cellIndex;
	// while (rowIterator.hasNext()) {
	// cellIndex = 0;
	// Row row = rowIterator.next();
	//
	// for (int i = 0; i < 20; i++) {
	// if (row.getCell(i) == null)
	// row.createCell(i);
	// }
	//
	// fileFieldsList.add(new FileFields(row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue(), row.getCell(cellIndex++)
	// .getStringCellValue()));
	//
	// }
	//
	// workBook.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return fileFieldsList;
	// }
}