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
import com.snapdeal.sps.intersectISBN.dto.CatSubcatDTO;
import com.snapdeal.sps.intersectISBN.dto.NavigationCategoryDTO;
import com.snapdeal.sps.intersectISBN.dto.PriceInventoryDTO;

public class DataUtilities {

	public static Map<String, CatSubcatDTO> subCategoryCodeSubCategoryMap = new HashMap<String, CatSubcatDTO>();
	public static Map<String, String> bindingMap = new HashMap<String, String>();
	public static Set<String> restrictedBindingSet = new HashSet<String>();
	public static Set<String> isbns50k = new HashSet<String>();
	public static Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
	public static Set<String> restrictedWordsSet = new HashSet<String>();
	public static Set<String> processedIsbnSet = new HashSet<String>();
	public static Set<String> imageNameSet = new HashSet<String>();
	public static Set<String> disabledIsbns = new HashSet<String>();
	public static Set<String> activeIsbns = new HashSet<String>();
	public static Map<CatSubcatDTO, NavigationCategoryDTO> subcategoryNavigationCategoryMap = new HashMap<CatSubcatDTO, NavigationCategoryDTO>();

	public static void loadProgramData() {

		bindingMap = initializeBindingMap(new File(
				Constants.BINDING_MAP_EXCEL_PATH));
		subCategoryCodeSubCategoryMap = getSubCategoryCodeSubCategoryMap(new File(
				Constants.CATEGORY_MAPPING_EXCEL_PATH));
		restrictedBindingSet = getFirstCellDataSetFromExcel(new File(
				Constants.RESTRICTED_BINDING_EXCEL_PATH));
		// isbns50k = getFirstCellDataSetFromExcel(new
		// File(Constants.ISBNS_50K_PATH));
		// isbnPriceInventoryMap = getisbnPriceInventoryMap(new
		// File(Constants.PRICE_INVENTORY_EXCEL_PATH));

		// restrictedWordsSet = getFirstCellDataSetFromExcel(new
		// File(Constants.RESTRICTED_WORDS_EXCEL_PATH));
		processedIsbnSet = getFirstCellDataSetFromExcel(new File(
				Constants.PROCESSED_SKU_EXCEL_PATH));
		imageNameSet = getImageNames(new File(Constants.IMAGE_FILES_PATH));
		subcategoryNavigationCategoryMap = getNavigationCategoryDTO(new File(
				Constants.NAVIGATION_CATEGORY_EXCEL_PATH));
		isbnPriceInventoryMap = getisbnPriceInventoryMapCSV(new File(
				Constants.PRICE_INVENTORY_EXCEL_PATH), "[ ,\t	]");

		disabledIsbns = MysqlDao.getDisabledIsbns();
		activeIsbns = MysqlDao.getActiveIsbns();

		System.out.println("disabled : " + disabledIsbns.size());
		System.out.println("enabled : " + activeIsbns.size());
		// System.out.println(bindingMap);
		// System.out.println(subCategoryCodeSubCategoryMap);
		// System.out.println(restrictedBindingSet);
		// System.out.println(isbns50k);
		// System.out.println(isbnPriceInventoryMap);
		System.out.println(imageNameSet);
		System.out.println("image set size " + imageNameSet.size());
		System.out.println("isbn price size " + isbnPriceInventoryMap.size());
		System.out.println("processed size " + processedIsbnSet.size());

	}

	private static Set<String> getDisabledIsbns() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(Constants.OLD_ISBN_CSV_PATH))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line;
		Set<String> set = new HashSet<String>();
		try {
			while ((line = reader.readLine()) != null) {
				set.add(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	private static Set<String> getImageNames(File fileDir) {
		System.out.println(fileDir.getAbsolutePath());
		Set<String> set = new HashSet<String>();
		// File[] dir = fileDir.listFiles();
		String[] images = fileDir.list();

		/*
		 * for (File f : dir) { try { String[] images = f.list();
		 */
		try {
			for (String file : images) {
				try {
					// System.out.println("file name"+file);
					String name = file.substring(0, file.lastIndexOf("."));
					if (!processedIsbnSet.contains(name)) {
						set.add(name);
					}
				} catch (Exception e) {
					System.out.println("Error file name:" + file);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * } catch (Exception e) { e.printStackTrace(); } }
		 */
		return set;
	}

	private static Map<String, PriceInventoryDTO> getisbnPriceInventoryMapCSV(
			File file, String delimiter) {
		Map<String, PriceInventoryDTO> isbnPriceInventoryMap = new HashMap<String, PriceInventoryDTO>();
		try {

			System.out
					.println("Inside  getisbnPriceInventoryMapCSV().\nGoing to read file:"
							+ file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String line = "";

			while ((line = reader.readLine()) != null) {
				try{
				String[] columns = line.split(delimiter);
				if (!processedIsbnSet.contains(columns[0].trim().toLowerCase())) {
					isbnPriceInventoryMap.put(columns[0].trim().toLowerCase(),
							new PriceInventoryDTO(columns[1], columns[2]));
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("isbnPriceInventoryMap size : "
				+ isbnPriceInventoryMap.size());
		return isbnPriceInventoryMap;
	}

	private static Map<String, PriceInventoryDTO> getisbnPriceInventoryMap(
			File file) {
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

				for (int i = 0; i < 3; i++) {
					if (row.getCell(i) == null)
						row.createCell(i);
				}

				for (int i = 0; i < 3; i++) {
					row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
				}
				Cell isbn = row.getCell(0);
				Cell price = row.getCell(1);
				Cell inventory = row.getCell(2);
				isbnPriceInventoryMap.put(isbn.getStringCellValue().trim()
						.toLowerCase(), new PriceInventoryDTO(price
						.getStringCellValue().trim(), inventory
						.getStringCellValue().trim()));
			}

			myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isbnPriceInventoryMap;
	}

	private static Map<CatSubcatDTO, NavigationCategoryDTO> getNavigationCategoryDTO(
			File file) {
		Map<CatSubcatDTO, NavigationCategoryDTO> subcategoryNavigationCategoryMap = new HashMap<CatSubcatDTO, NavigationCategoryDTO>();
		try {

			System.out
					.println("Inside  getNavigationCategoryDTO().\nGoing to read file:"
							+ file);
			OPCPackage pkg = OPCPackage.open(file);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				try {
				for (int i = 0; i < 4; i++) {
					if (row.getCell(i) == null)
						row.createCell(i);
				}

				for (int i = 0; i < 4; i++) {
					row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
				}
				Cell subCatLevel1 = row.getCell(0);
				Cell subCatLevel2 = row.getCell(1);
				Cell productCategory = row.getCell(2);
				Cell navigationCategory = row.getCell(3);
				subcategoryNavigationCategoryMap
						.put(new CatSubcatDTO(subCatLevel1.getStringCellValue()
								.trim().toLowerCase(), subCatLevel2.getStringCellValue()
								.trim().toLowerCase()), new NavigationCategoryDTO(
								productCategory.getStringCellValue().trim().toLowerCase(),
								navigationCategory.getStringCellValue().trim().toLowerCase()));
				} catch (Exception e){
					e.printStackTrace();
				}
			}

			myWorkBook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subcategoryNavigationCategoryMap;
	}

	private static Map<String, CatSubcatDTO> getSubCategoryCodeSubCategoryMap(
			File file) {

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

				for (int i = 0; i < 5; i++) {
					if (row.getCell(i) == null)
						row.createCell(i);
				}
				Cell bms = row.getCell(0);
				Cell subCatLevel1 = row.getCell(4);
				Cell subCatLevel2 = row.getCell(5);
				// System.out.println("subCatLevel1:"+subCatLevel1);
				// System.out.println("subCatLevel2:"+subCatLevel2);
				try {
					CatSubcatDTO catSubcatDTO = new CatSubcatDTO(subCatLevel1
							.getStringCellValue().trim().toLowerCase(), subCatLevel2
							.getStringCellValue().trim().toLowerCase());
					subCategoryCodeSubCategoryMap.put(bms.getStringCellValue()
							.trim().toLowerCase(), catSubcatDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}
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

	private static Map<String, String> initializeBindingMap(File file) {
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
				for (int i = 0; i < 2; i++) {
					if (row.getCell(i) == null)
						row.createCell(i);
				}
				Cell originalBinding = row.getCell(0);
				Cell mappedBinding = row.getCell(1);
				try {
					bindingMap.put(originalBinding.getStringCellValue()
							.toLowerCase().trim(), mappedBinding
							.getStringCellValue().trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
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

	private static Set<String> getFirstCellDataSetFromExcel(File file) {
		Set<String> stringSet = new HashSet<String>();
		try {
			System.out
					.println("Inside  getFirstCellDataSetFromExcel().\nGoing to read file:"
							+ file);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String line;
			while ((line = reader.readLine()) != null) {
				try {
					stringSet.add(line.toLowerCase().trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

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
