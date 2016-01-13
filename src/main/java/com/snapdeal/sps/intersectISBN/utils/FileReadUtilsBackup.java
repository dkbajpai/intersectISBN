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
import com.snapdeal.sps.intersectISBN.dto.DimensionsDTO;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.ResultDTO;
import com.snapdeal.sps.intersectISBN.dto.ProcessedDTO;
import com.snapdeal.sps.intersectISBN.enums.AcceptedFileHeaders;
import com.snapdeal.sps.intersectISBN.enums.RejectedFileHeaders;

public class FileReadUtilsBackup {

	public static void readInputTextAndWriteXlsx(File file, String path,
			String fileName, final int BATCHSIZE) {
		int acceptedItr = 0;
		int rejectedItr = 0;

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

					if(DataUtilities.isbns50k.contains(fileFields.getIsbn13())) {
						fileFieldList.add(fileFields);
					}
					
					fileFields = new FileFields();
					if (row.contains("**END"))
						resultDTO.setTotalRecordsInFile(row.replace(
								"**END,", "").trim());
				}

				if (fileFieldList.size() % BATCHSIZE == 0) {
					ProcessedDTO dto = DataValidator.processData(fileFieldList);
					processedDTO
							.setAllAcceptedRecords(dto.getAcceptedRecords());
					processedDTO
							.setAllRejectedRecords(dto.getRejectedRecords());

					fileFieldList.clear();
				}

				if (processedDTO != null
						&& processedDTO.getAcceptedRecords().size() >= BATCHSIZE) {

					FileWriteUtils
							.writeXLSXInValidatorFormat(
									processedDTO.getAcceptedRecords(),
									AcceptedFileHeaders.values(),
									DataUtilities.subCategoryCodeSubCategoryMap,
									Constants.WORKING_DIRECTORY
											+ Constants.ACCEPTED_FILES_DIRECTORY,
									"Accepted_Book_Listing" + (++acceptedItr)
											+ ".xlsx");
					resultDTO.setAccptedRecords(resultDTO
							.getAccptedRecords()
							+ processedDTO.getAcceptedRecords().size());
					processedDTO.getAcceptedRecords().clear();

				}

				if (processedDTO != null
						&& processedDTO.getRejectedRecords().size() >= BATCHSIZE) {
					FileWriteUtils
							.writeRejectedXlsx(
									processedDTO.getRejectedRecords(),
									RejectedFileHeaders.values(),
									DataUtilities.subCategoryCodeSubCategoryMap,
									Constants.WORKING_DIRECTORY
											+ Constants.REJECTED_FILES_DIRECTORY,
									"Rejected_Book_Listing" + (++rejectedItr)
											+ ".xlsx");
					resultDTO.setRejectedRecords(resultDTO
							.getRejectedRecords()
							+ processedDTO.getRejectedRecords().size());
					System.out.println("SIZE():"+ processedDTO.getRejectedRecords().size());
					processedDTO.getRejectedRecords().clear();
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

			ProcessedDTO dto = DataValidator.processData(fileFieldList);
			processedDTO.setAllAcceptedRecords(dto.getAcceptedRecords());
			processedDTO.setAllRejectedRecords(dto.getRejectedRecords());

			fileFieldList.clear();

			System.out.println("Going to write accepted records.");
			FileWriteUtils.writeXLSXInValidatorFormat(processedDTO.getAcceptedRecords(),
					AcceptedFileHeaders.values(),
					DataUtilities.subCategoryCodeSubCategoryMap,
					Constants.WORKING_DIRECTORY
							+ Constants.ACCEPTED_FILES_DIRECTORY,
					"Accepted_Book_Listing" + (++acceptedItr) + ".xlsx");
			resultDTO.setAccptedRecords(resultDTO.getAccptedRecords()
					+ processedDTO.getAcceptedRecords().size());

			
			System.out.println("Going to write rejected records.");
			FileWriteUtils.writeRejectedXlsx(processedDTO.getRejectedRecords(),
					RejectedFileHeaders.values(),
					DataUtilities.subCategoryCodeSubCategoryMap,
					Constants.WORKING_DIRECTORY
							+ Constants.REJECTED_FILES_DIRECTORY,
					"Rejected_Book_Listing" + (++rejectedItr) + ".xlsx");
			resultDTO.setRejectedRecords(resultDTO.getRejectedRecords()
					+ processedDTO.getRejectedRecords().size());
			System.out.println("SIZE():"+ processedDTO.getRejectedRecords().size());
			processedDTO.getRejectedRecords().clear();

			
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