package com.snapdeal.sps.intersectISBN.dataFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.snapdeal.sps.intersectISBN.db.MysqlDao;
import com.snapdeal.sps.intersectISBN.dto.PriceInventoryDTO;



public class DataUtilities {

	public static Map<String, String> subCategoryCodeSubCategoryMap = new HashMap<String, String>();
	public static Map<String, String> bindingMap = new HashMap<String, String>();
	public static Set<String> restrictedBindingSet = new HashSet<String>();
	public static Set<String> isbns50k = new HashSet<String>();
	public static Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
	public static Set<String> restrictedWordsSet = new HashSet<String>();
	public static Set<String> processedIsbnSet = new HashSet<String>();
	public static Set<String> imageNameSet = new HashSet<String>();
	public static Set<String> disabledIsbns = new HashSet<String>();
	public static Set<String> activeIsbns = new HashSet<String>();
	
	public static void loadProgramData(){
		
		bindingMap = initializeBindingMap(new File(Constants.BINDING_MAP_EXCEL_PATH));
		subCategoryCodeSubCategoryMap =	getSubCategoryCodeSubCategoryMap(new File(Constants.CATEGORY_MAPPING_EXCEL_PATH));
		restrictedBindingSet = getFirstCellDataSetFromExcel(new File(Constants.RESTRICTED_BINDING_EXCEL_PATH));
		isbns50k = getFirstCellDataSetFromExcel(new File(Constants.ISBNS_50K_PATH));
		//isbnPriceInventoryMap = getisbnPriceInventoryMap(new File(Constants.PRICE_INVENTORY_EXCEL_PATH));
		isbnPriceInventoryMap = getisbnPriceInventoryMapCSV(new File(Constants.PRICE_INVENTORY_EXCEL_PATH), " ");
		restrictedWordsSet = getFirstCellDataSetFromExcel(new File(Constants.RESTRICTED_WORDS_EXCEL_PATH));
		processedIsbnSet = getFirstCellDataSetFromExcel(new File(Constants.PROCESSED_SKU_EXCEL_PATH));
		
		disabledIsbns = MysqlDao.getDisabledIsbns();
		activeIsbns = MysqlDao.getActiveIsbns();
		
		System.out.println("disabled : " + disabledIsbns.size());
		System.out.println("enabled : " + activeIsbns.size());
//		System.out.println(bindingMap);
//		System.out.println(subCategoryCodeSubCategoryMap);
//		System.out.println(restrictedBindingSet);
//		System.out.println(isbns50k);
//		System.out.println(isbnPriceInventoryMap);
//		System.out.println(restrictedWordsSet);
		
		//System.out.println(isbnPriceInventoryMap.size());
		
	}

	private static Map<String, PriceInventoryDTO> getisbnPriceInventoryMapCSV(File file, String delimiter) {
		 Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
		try {
			
			System.out
					.println("Inside  getisbnPriceInventoryMapCSV().\nGoing to read file:"
							+ file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				
				String[] columns = line.split(delimiter);
				isbnPriceInventoryMap.put(columns[0].trim().toLowerCase(), new PriceInventoryDTO(columns[1],columns[2]));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		System.out.println("isbnPriceInventoryMap size : " +isbnPriceInventoryMap.size());
		return isbnPriceInventoryMap;
	}
	
	

	private static Map<String, PriceInventoryDTO> getisbnPriceInventoryMap(File file) {
		 Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
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
				isbnPriceInventoryMap.put(isbn.getStringCellValue().trim().toLowerCase(),new PriceInventoryDTO(price.getStringCellValue().trim(), inventory.getStringCellValue().trim()));
			}

			myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("isbnPriceInventoryMap size : " +isbnPriceInventoryMap.size());
		return isbnPriceInventoryMap;
	}
	
	private static Map<String, String> getSubCategoryCodeSubCategoryMap(File file) {
		
		  Map<String, String> subCategoryCodeSubCategoryMap = new HashMap<String, String>();
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
				subCategoryCodeSubCategoryMap.put(bms.getStringCellValue().trim().toLowerCase(),
						subCat.getStringCellValue().trim());
			}

			myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return subCategoryCodeSubCategoryMap;
	}
	
	private  static Map<String, String> initializeBindingMap(File file) {
		Map<String, String> bindingMap = new HashMap<String, String>();
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
				bindingMap.put(originalBinding.getStringCellValue().toLowerCase().trim(),
						mappedBinding.getStringCellValue().trim());
			}
			
		myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bindingMap;
	}
		
	private  static Set<String> getFirstCellDataSetFromExcel(File file) {
		Set<String> stringSet = new HashSet<String>();
		try {
			System.out
					.println("Inside  getFirstCellDataSetFromExcel().\nGoing to read file:"
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
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				Cell cell = row.getCell(0);
				stringSet.add(cell.getStringCellValue().toLowerCase().trim());
			}
			
			myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringSet;
	}
	
		
	
}
