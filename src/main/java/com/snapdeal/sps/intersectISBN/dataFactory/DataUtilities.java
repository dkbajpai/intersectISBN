package com.snapdeal.sps.intersectISBN.dataFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.snapdeal.sps.intersectISBN.dto.PriceInventoryDTO;



public class DataUtilities {

	public static Map<String, String> subCategoryCodeSubCategoryMap = new HashMap<String, String>();
	public static Map<String, String> bindingMap = new HashMap<String, String>();
	public static Set<String> restrictedBindingSet = new HashSet<String>();
	public static Set<String> isbns50k = new HashSet<String>();
	public static Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
	
	public static void loadProgramData(){
		
		initializeBindingMap(new File(Constants.BINDING_MAP_EXCEL_PATH));
		getSubCategoryCodeSubCategoryMap(new File(Constants.CATEGORY_MAPPING_EXCEL_PATH));
		getRestrictedBinding(new File(Constants.RESTRICTED_BINDING_EXCEL_PATH));
		getIsbns50k(new File(Constants.ISBNS_50K_PATH));
		getisbnPriceInventoryMap(new File(Constants.PRICE_INVENTORY_EXCEL_PATH));
		
	}


	private static void getisbnPriceInventoryMap(File file) {
		try {
			
			System.out
					.println("Inside  getisbnPriceInventoryMap().\nGoing to read file:"
							+ file);
			OPCPackage pkg = OPCPackage.open(file);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				for(int i = 0; i < 3; i++){
					if(row.getCell(i) == null)
						row.createCell(i);
				}
				
				for(int i = 0; i < 3; i++){
					row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
				}
				Cell isbn = row.getCell(0);
				Cell price = row.getCell(1);
				Cell inventory = row.getCell(2);
				isbnPriceInventoryMap.put(isbn.getStringCellValue(),new PriceInventoryDTO(price.getStringCellValue(), inventory.getStringCellValue()));
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
	
	
	private static void getSubCategoryCodeSubCategoryMap(File file) {
		try {
			
			System.out
					.println("Inside  getSubCategoryCodeSubCategoryMap().\nGoing to read file:"
							+ file);
			OPCPackage pkg = OPCPackage.open(file);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				for(int i = 0; i < 2; i++){
					if(row.getCell(i) == null)
						row.createCell(i);
				}
				Cell bms = row.getCell(0);
				Cell subCat = row.getCell(4);
				subCategoryCodeSubCategoryMap.put(bms.getStringCellValue(),
						subCat.getStringCellValue());
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
	
	private  static void initializeBindingMap(File file) {
		try {
			System.out
					.println("Inside  initializeBindingMap().\nGoing to read file:"
							+ file);
			OPCPackage pkg = OPCPackage.open(file);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				for(int i = 0; i < 2; i++){
					if(row.getCell(i) == null)
						row.createCell(i);
				}
				Cell originalBinding = row.getCell(0);
				Cell mappedBinding = row.getCell(1);
				bindingMap.put(originalBinding.getStringCellValue().toLowerCase(),
						mappedBinding.getStringCellValue());
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
	
	
	private  static void getRestrictedBinding(File file) {
		try {
			System.out
					.println("Inside  getRestrictedBinding().\nGoing to read file:"
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
				restrictedBindingSet.add(binding.getStringCellValue().toLowerCase());
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
}
