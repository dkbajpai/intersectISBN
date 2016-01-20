package com.snapdeal.sps.intersectISBN.dataFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;



public class Constants {

	public static final int BATCHSIZE;
	
	public static final String INPUT_TXT_DIRECTORY_PATH;
	public static final String WORKING_DIRECTORY;
	public static final String ALL_FILES_DIRECTORY;
	public static final String ACCEPTED_FILES_DIRECTORY;
	public static final String REJECTED_FILES_DIRECTORY;
	
	public static final String ISBNS_50K_PATH;
	public static final String BINDING_MAP_EXCEL_PATH;
	public static final String RESTRICTED_BINDING_EXCEL_PATH;
	public static final String CATEGORY_MAPPING_EXCEL_PATH;
	public static final String PRICE_INVENTORY_EXCEL_PATH;
	public static final String RESTRICTED_WORDS_EXCEL_PATH;
	public static final String PROCESSED_SKU_EXCEL_PATH;
	
	public static final String LOCAL_DB_URL;
	public static final String LOCAL_DB_USER;
	public static final String LOCAL_DB_PASS;
	
	public static final String DWH_DB_URL;
	public static final String DWH_DB_USER;
	public static final String DWH_DB_PASS;


	public static final String IMAGE_FILES_PATH;

	
	static {
		Properties prop = new Properties();
		InputStream input = null;

		try {
		
			input = new FileInputStream(System.getProperty("user.dir")
					+ "/config.properties");
			
			/*input = new FileInputStream("config.properties");*/
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception in loading properties");
		}

		BATCHSIZE = Integer.parseInt(prop.getProperty("BATCHSIZE"));
		
		INPUT_TXT_DIRECTORY_PATH = prop.getProperty("INPUT_TXT_DIRECTORY_PATH");
		WORKING_DIRECTORY = prop.getProperty("WORKING_DIRECTORY");
		ALL_FILES_DIRECTORY = prop.getProperty("ALL_FILES_DIRECTORY");
		ACCEPTED_FILES_DIRECTORY = prop.getProperty("ACCEPTED_FILES_DIRECTORY");
		REJECTED_FILES_DIRECTORY = prop.getProperty("REJECTED_FILES_DIRECTORY");
		
		ISBNS_50K_PATH = prop.getProperty("ISBNS_50K_PATH");
		BINDING_MAP_EXCEL_PATH = prop.getProperty("BINDING_MAP_EXCEL_PATH");
		RESTRICTED_BINDING_EXCEL_PATH = prop.getProperty("RESTRICTED_BINDING_EXCEL_PATH");
		CATEGORY_MAPPING_EXCEL_PATH = prop.getProperty("CATEGORY_MAPPING_EXCEL_PATH");
		PRICE_INVENTORY_EXCEL_PATH = prop.getProperty("PRICE_INVENTORY_EXCEL_PATH");
		RESTRICTED_WORDS_EXCEL_PATH = prop.getProperty("RESTRICTED_WORDS_EXCEL_PATH");
		PROCESSED_SKU_EXCEL_PATH = prop.getProperty("PROCESSED_SKU_EXCEL_PATH");

		
		LOCAL_DB_URL = prop.getProperty("LOCAL_DB_URL");
		LOCAL_DB_USER = prop.getProperty("LOCAL_DB_USER");
		LOCAL_DB_PASS = prop.getProperty("LOCAL_DB_PASS");
		
		DWH_DB_URL = prop.getProperty("DWH_DB_URL");
		DWH_DB_USER = prop.getProperty("DWH_DB_USER");
		DWH_DB_PASS = prop.getProperty("DWH_DB_PASS");

		IMAGE_FILES_PATH = prop.getProperty("IMAGE_FILES_PATH");

		
	}


}
