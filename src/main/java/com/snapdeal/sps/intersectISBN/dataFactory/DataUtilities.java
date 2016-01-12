package com.snapdeal.sps.intersectISBN.dataFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtilities {

	public static Map<String, String> subCategoryCodeSubCategoryMap;
	public static Map<String, String> bindingMap;
	public static Set<String> restrictedBindingSet;
	
	public static void loadProgramData(){
		
		initializeBindingMap(new File(""));
		initializeSubCategoryCodeSubCategoryMap(new File(""));
		getRestrictedBinding(new File(""));
	}

	private static void initializeSubCategoryCodeSubCategoryMap(File file) {
		try {
			
			System.out
					.println("Inside  initializeSubCategoryCodeSubCategoryMap().\nGoing to read file:"
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
}
