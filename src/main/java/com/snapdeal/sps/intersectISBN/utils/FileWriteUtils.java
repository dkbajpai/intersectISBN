package com.snapdeal.sps.intersectISBN.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.NavigationCategoryDTO;
import com.snapdeal.sps.intersectISBN.dto.PriceInventoryDTO;
import com.snapdeal.sps.intersectISBN.dto.RejectedDTO;
import com.snapdeal.sps.intersectISBN.enums.AcceptedFileHeaders;
import com.snapdeal.sps.intersectISBN.enums.RejectedFileHeaders;
import com.snapdeal.sps.intersectISBN.enums.ValidatorFileHeaders;
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

	public static int writeJugadFucntion(Set<String> emptyData, File fileName) {

		System.out
				.println("Inside writeFileFieldsXlsx().\nGoing to write file:"
						+ fileName);

		Workbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Sheet1");

		Row row;
		int currentRow = 0;
		int cellIndex = 0;
		try {
			row = sheet.createRow(currentRow++);

			for (String empty : emptyData) {
				if (cellIndex % 10 == 0) {
					row = sheet.createRow(currentRow++);
					cellIndex = 0;
				}

				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(empty);

			}
			try {

				FileOutputStream fileOut = new FileOutputStream(fileName);
				workbook.write(fileOut);

				fileOut.flush();
				fileOut.close();
				workbook.close();
				System.out.println("Written " + fileName + " successfully.");
			} catch (Exception e) {
				e.printStackTrace();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentRow - 1;
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
				cell.setCellValue(GeneralUtils
						.getValidDescriptionText(fileFields));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getNumberOfPages());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Utils.formatTimeStamp(fileFields
						.getPublicationDate()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getCategoryCode());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLanguage(fileFields
						.getLanguage()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBinding(fileFields
						.getBinding()));

			}
			try {

				FileOutputStream fileOut = new FileOutputStream(path + fileName);
				workbook.write(fileOut);

				fileOut.flush();
				fileOut.close();
				workbook.close();
				System.out.println("Written " + path + fileName
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

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (AcceptedFileHeaders header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header.getHeader());
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
				cell.setCellValue(GeneralUtils
						.getValidDescriptionText(fileFields));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getNumberOfPages());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Utils.formatTimeStamp(fileFields
						.getPublicationDate()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLanguage(fileFields
						.getLanguage()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBinding(fileFields
						.getBinding()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Books");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidChildCategory(fileFields
						.getCategoryCode()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLength(fileFields
						.getLength()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBreadth(fileFields
						.getBreadth()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidHeight(fileFields
						.getHeight()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidWeight(fileFields
						.getWeight()));

			}
			try {

				FileOutputStream fileOut = new FileOutputStream(path + fileName);
				workbook.write(fileOut);

				fileOut.flush();
				fileOut.close();
				workbook.close();
				System.out.println("Written " + path + fileName
						+ " successfully.");
			} catch (Exception e) {
				e.printStackTrace();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentRow - 1;
	}
	
	
	
	

	public static int writeXLSXInConverterFormat(
			List<FileFields> fileFieldsList, AcceptedFileHeaders[] headers,
			Map<String, String> subCategoryCodeSubCategoryMap, String path,
			String fileName,
			Map<String, PriceInventoryDTO> isbnPriceInventoryMap) {

		System.out.println("Inside writeAcceptedXlsx().\nGoing to write file:"
				+ path + fileName);

		makeDir(path);

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
		Set<File> imageFileSet = new HashSet<File>();

		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (AcceptedFileHeaders header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header.getHeader());
			}

			for (FileFields fileFields : fileFieldsList) {
				cellIndex = 0;
				row = sheet.createRow(currentRow++);

				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getTitle());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getAuthors());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getPublisher());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils
						.getValidDescriptionText(fileFields));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBinding(fileFields
						.getBinding()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getNumberOfPages());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Utils.formatTimeStamp(fileFields
						.getPublicationDate()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLanguage(fileFields
						.getLanguage()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Books");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidChildCategory(fileFields
						.getCategoryCode()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidChildCategory(fileFields
						.getCategoryCode()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getIsbn10());

				// MRP
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(
						fileFields.getIsbn13().toLowerCase()).getPrice());

				// SELLING PRICE
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(
						fileFields.getIsbn13().toLowerCase()).getPrice());

				// INVENTORY
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(
						fileFields.getIsbn13().toLowerCase()).getInventory());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidWeight(fileFields
						.getWeight()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLength(fileFields
						.getLength()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidHeight(fileFields
						.getHeight()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBreadth(fileFields
						.getBreadth()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Dropshipment");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("AIR");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("NO");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("15");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("39954");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Integer.toString(currentRow - 1));

				imageFileSet.add(new File(Constants.IMAGE_FILES_PATH
						+ fileFields.getIsbn13().trim().toLowerCase() + ".jpg"));

			}
			try {

				FileOutputStream fileOut = new FileOutputStream(path + fileName);
				workbook.write(fileOut);

				GeneralUtils.zipFile(imageFileSet,
						path + fileName.substring(0, fileName.lastIndexOf("."))
								+ ".zip");

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
	
	
	
	public static int writeXLSXInValidatorFormat(
			List<FileFields> fileFieldsList, ValidatorFileHeaders[] headers,
			Map<String, String> subCategoryCodeSubCategoryMap, String path,
			String fileName,
			Map<String, PriceInventoryDTO> isbnPriceInventoryMap,Map<String, NavigationCategoryDTO> subcategoryNavigationMap) {

		System.out.println("Inside writeXLSXInValidatorFormat().\nGoing to write file:"
				+ path + fileName);

		makeDir(path);

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
		Set<File> imageFileSet = new HashSet<File>();

		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (ValidatorFileHeaders header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header.getHeader());
			}

			for (FileFields fileFields : fileFieldsList) {
				cellIndex = 0;
				row = sheet.createRow(currentRow++);

				//OFFER
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidIsbn(fileFields.getIsbn13(), Constants.OLD_SKU_SUFFIX));
				
				//Vendor Code
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.VENDOR_CODE);
				
				//Product Name
				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getTitle());
				
				//SKU
				cell = row.createCell(cellIndex++);
				cell.setCellValue(fileFields.getIsbn13());

				//Highlights
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getHighlights(fileFields));
				
				//Sub-Title
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				
				//Description
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils
						.getValidDescriptionText(fileFields));
				
				
				//Tech Speccs
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");

				

				//Brand id
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.BRAND_ID);
				

				//Size chart id
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//Length 
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLength(fileFields
						.getLength()));
				//height
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidHeight(fileFields
					.getHeight()));
				
				//Width
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBreadth(fileFields
						.getBreadth()));
				
				//Shipping weight
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidWeight(fileFields
						.getWeight()));

				

				//shipping Length 
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLength(fileFields
						.getLength()));
				

				//shipping Width
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBreadth(fileFields
						.getBreadth()));
				
				
				//shipping height
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidHeight(fileFields
					.getHeight()));
				
				//start date
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getDateTime(0));
				
				//end date
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getDateTime(2));
				
				//BD EMAil
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.BD_EMAIL);
				
				//Att1
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//Att2
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//Att3
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//Select category
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//Product category
				cell = row.createCell(cellIndex++);
				cell.setCellValue(subcategoryNavigationMap.get(subCategoryCodeSubCategoryMap.get(fileFields.getCategoryCode())).getProductCategory());
				
				//filter1
				cell = row.createCell(cellIndex++);
				cell.setCellValue("Language");
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLanguage(fileFields.getLanguage()));
				
				//filter2
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter3
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter4
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter5
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter6
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter7
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter8
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter9
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//filter10
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				
				//image1
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidIsbn(fileFields.getIsbn13(),Constants.OLD_SKU_SUFFIX) + ".jpg");
				
				//image2
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image3
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image4
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image5
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image6
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image7
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image8
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image9
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image10
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image11
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//image12
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//inventory
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(GeneralUtils.getValidIsbn(fileFields.getIsbn13(), Constants.OLD_SKU_SUFFIX)).getInventory());

				//procurement sla
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.PROCUREMENT_SLA);
				
				//warehouse sla
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.WAREHOUSE_PROCESSING_SLA);
				
				//mrp
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(GeneralUtils.getValidIsbn(fileFields.getIsbn13(), Constants.OLD_SKU_SUFFIX)).getPrice());

				//selling price
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(GeneralUtils.getValidIsbn(fileFields.getIsbn13(), Constants.OLD_SKU_SUFFIX)).getPrice());
				
				//vendor price
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.VENDOR_PRICE);
				
				//service tax
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.SERVICE_TAX);
				
				//sd commision
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.SD_COMMISION);
				
				//courier cost
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.COURIER_COST);
				
				//fulfilllment
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.FULFILLMENT_BY);
				
				
				// weight
				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidWeight(fileFields
						.getWeight()));
				
				//courier cost bourne
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.COURIER_COST_BOURNE_BY);
				
				//vendor enabled
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.VENDOR_ENABLED);
				
				//shipping group
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.SHIPPING_GROUP);
				
				//servicibilty index
				cell = row.createCell(cellIndex++);
				cell.setCellValue(Constants.SERVICIBILITY_INDEX);
				
				//freebie
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");

				//upc
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//ean
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//mpn
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//model number
				cell = row.createCell(cellIndex++);
				cell.setCellValue("");
				
				//navigation
				cell = row.createCell(cellIndex++);
				cell.setCellValue(subcategoryNavigationMap.get(subCategoryCodeSubCategoryMap.get(fileFields.getCategoryCode())).getNavigationCategory());
				
				imageFileSet.add(new File(Constants.IMAGE_FILES_PATH
						+ fileFields.getIsbn13().trim().toLowerCase() + ".jpg"));

			}
			try {

				FileOutputStream fileOut = new FileOutputStream(path + fileName);
				workbook.write(fileOut);

				GeneralUtils.zipFile(imageFileSet,
						path + fileName.substring(0, fileName.lastIndexOf("."))
								+ ".zip");

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
			String fileName,
			Map<String, PriceInventoryDTO> isbnPriceInventoryMap) {

		System.out.println("Inside writeRejectedXlsx().\nGoing to write file:"
				+ path + fileName);

		makeDir(path);

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		Row row;
		int currentRow = 0;
		try {
			row = sheet.createRow(currentRow++);
			int cellIndex = 0;
			for (RejectedFileHeaders header : headers) {
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(header.getHeader());
			}

			for (RejectedDTO rejectedDTO : rejectedDTOList) {
				cellIndex = 0;
				row = sheet.createRow(currentRow++);

				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getTitle());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getAuthors());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getPublisher());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils
						.getValidDescriptionText(rejectedDTO.getFileFields()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getBinding());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields()
						.getNumberOfPages());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Utils.formatTimeStamp(rejectedDTO
						.getFileFields().getPublicationDate()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLanguage(rejectedDTO
						.getFileFields().getLanguage()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Books");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils
						.getValidChildCategory(rejectedDTO.getFileFields()
								.getCategoryCode()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils
						.getValidChildCategory(rejectedDTO.getFileFields()
								.getCategoryCode()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getIsbn10());

				// MRP
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(
						rejectedDTO.getFileFields().getIsbn13().toLowerCase())
						.getPrice());

				// SELLING PRICE
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(
						rejectedDTO.getFileFields().getIsbn13().toLowerCase())
						.getPrice());

				// INVENTORY
				cell = row.createCell(cellIndex++);
				cell.setCellValue(isbnPriceInventoryMap.get(
						rejectedDTO.getFileFields().getIsbn13().toLowerCase())
						.getInventory());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidWeight(rejectedDTO
						.getFileFields().getWeight()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidLength(rejectedDTO
						.getFileFields().getLength()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidHeight(rejectedDTO
						.getFileFields().getHeight()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue(GeneralUtils.getValidBreadth(rejectedDTO
						.getFileFields().getBreadth()));

				cell = row.createCell(cellIndex++);
				cell.setCellValue("Dropshipment");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("AIR");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("NO");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("15");

				cell = row.createCell(cellIndex++);
				cell.setCellValue("39954");

				cell = row.createCell(cellIndex++);
				cell.setCellValue(rejectedDTO.getFileFields().getIsbn13());

				cell = row.createCell(cellIndex++);
				cell.setCellValue(Integer.toString(currentRow - 1));

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
						+ " successfully. No of rows:" + (currentRow - 1));
			} catch (Exception e) {
				e.printStackTrace();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentRow - 1;
	}
}