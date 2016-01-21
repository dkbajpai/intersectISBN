package com.snapdeal.sps.intersectISBN.dataFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Constants {

	public static final int BATCHSIZE;
	
	public static final String OLD_SKU_SUFFIX;

	public static final String INPUT_TXT_DIRECTORY_PATH;
	public static final String WORKING_DIRECTORY;
	public static final String ALL_FILES_DIRECTORY;
	public static final String ACCEPTED_FILES_DIRECTORY;
	public static final String REJECTED_FILES_DIRECTORY;

	public static final String IMAGE_FILES_PATH;
	public static final String ISBNS_50K_PATH;
	public static final String BINDING_MAP_EXCEL_PATH;
	public static final String RESTRICTED_BINDING_EXCEL_PATH;
	public static final String CATEGORY_MAPPING_EXCEL_PATH;
	public static final String PRICE_INVENTORY_EXCEL_PATH;
	public static final String RESTRICTED_WORDS_EXCEL_PATH;
	public static final String PROCESSED_SKU_EXCEL_PATH;
	public static final String NAVIGATION_CATEGORY_EXCEL_PATH;

	public static final String LOCAL_DB_URL;
	public static final String LOCAL_DB_USER;
	public static final String LOCAL_DB_PASS;

	public static final String DWH_DB_URL;
	public static final String DWH_DB_USER;
	public static final String DWH_DB_PASS;

	public static final String VENDOR_CODE;
	public static final String BRAND_ID;

	public static final String DEFAULT_LENGTH;
	public static final String DEFAULT_WIDTH;
	public static final String DEFAULT_HEIGHT;
	public static final String DEFAULT_WEIGHT;
	
	public static final String BD_EMAIL;
	public static final String PROCUREMENT_SLA;
	public static final String WAREHOUSE_PROCESSING_SLA;
	public static final String VENDOR_PRICE;
	public static final String SERVICE_TAX;
	public static final String SD_COMMISION;
	public static final String COURIER_COST;
	public static final String FULFILLMENT_BY;
	public static final String COURIER_COST_BOURNE_BY;
	public static final String VENDOR_ENABLED;
	public static final String SHIPPING_GROUP;
	public static final String SERVICIBILITY_INDEX;
	
	
	public final static String FTP_IP="54.255.175.99";
	public final static int FTP_PORT=21;
	
	public final static String FTP_UPLOAD_SHEET_USERNAME="Upload.Validator"; //uploads
	public final static String FTP_UPLOAD_SHEET_PASSWORD="Jasper#987";
	public final static String FTP_UPLOAD_SHEET_LOCATION="uploads";
	public final static String FTP_REJECTED_SHEET_LOCATION="booksrejectedfiles";
	public final static String FTP_ARCHIVE_SHEET_LOCATION="booksarchivedfiles";
	public final static String BOOKS_SHEET_PREFIX="Books_Listing_";
	
	

	static {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(System.getProperty("user.dir")
					+ "/config.properties");

			/* input = new FileInputStream("config.properties"); */
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception in loading properties");
		}

		BATCHSIZE = Integer.parseInt(prop.getProperty("BATCHSIZE"));
		OLD_SKU_SUFFIX = prop.getProperty("OLD_SKU_SUFFIX");

		INPUT_TXT_DIRECTORY_PATH = prop.getProperty("INPUT_TXT_DIRECTORY_PATH");
		WORKING_DIRECTORY = prop.getProperty("WORKING_DIRECTORY");
		ALL_FILES_DIRECTORY = prop.getProperty("ALL_FILES_DIRECTORY");
		ACCEPTED_FILES_DIRECTORY = prop.getProperty("ACCEPTED_FILES_DIRECTORY");
		REJECTED_FILES_DIRECTORY = prop.getProperty("REJECTED_FILES_DIRECTORY");

		IMAGE_FILES_PATH = prop.getProperty("IMAGE_FILES_PATH");
		ISBNS_50K_PATH = prop.getProperty("ISBNS_50K_PATH");
		BINDING_MAP_EXCEL_PATH = prop.getProperty("BINDING_MAP_EXCEL_PATH");
		RESTRICTED_BINDING_EXCEL_PATH = prop
				.getProperty("RESTRICTED_BINDING_EXCEL_PATH");
		CATEGORY_MAPPING_EXCEL_PATH = prop
				.getProperty("CATEGORY_MAPPING_EXCEL_PATH");
		PRICE_INVENTORY_EXCEL_PATH = prop
				.getProperty("PRICE_INVENTORY_EXCEL_PATH");
		RESTRICTED_WORDS_EXCEL_PATH = prop
				.getProperty("RESTRICTED_WORDS_EXCEL_PATH");
		PROCESSED_SKU_EXCEL_PATH = prop.getProperty("PROCESSED_SKU_EXCEL_PATH");
		NAVIGATION_CATEGORY_EXCEL_PATH = prop
				.getProperty("NAVIGATION_CATEGORY_EXCEL_PATH");

		LOCAL_DB_URL = prop.getProperty("LOCAL_DB_URL");
		LOCAL_DB_USER = prop.getProperty("LOCAL_DB_USER");
		LOCAL_DB_PASS = prop.getProperty("LOCAL_DB_PASS");

		DWH_DB_URL = prop.getProperty("DWH_DB_URL");
		DWH_DB_USER = prop.getProperty("DWH_DB_USER");
		DWH_DB_PASS = prop.getProperty("DWH_DB_PASS");

		VENDOR_CODE = prop.getProperty("VENDOR_CODE");
		BRAND_ID = prop.getProperty("BRAND_ID");
		
		DEFAULT_LENGTH = prop.getProperty("DEFAULT_LENGTH");
		DEFAULT_WIDTH = prop.getProperty("DEFAULT_WIDTH");
		DEFAULT_HEIGHT = prop.getProperty("DEFAULT_HEIGHT");
		DEFAULT_WEIGHT = prop.getProperty("DEFAULT_WEIGHT");
		
		BD_EMAIL = prop.getProperty("BD_EMAIL");
		PROCUREMENT_SLA = prop.getProperty("PROCUREMENT_SLA");
		WAREHOUSE_PROCESSING_SLA = prop.getProperty("WAREHOUSE_PROCESSING_SLA");
		VENDOR_PRICE = prop.getProperty("VENDOR_PRICE");
		SERVICE_TAX = prop.getProperty("SERVICE_TAX");
		SD_COMMISION = prop.getProperty("SD_COMMISION");
		COURIER_COST = prop.getProperty("COURIER_COST");
		FULFILLMENT_BY = prop.getProperty("FULFILLMENT_BY");
		COURIER_COST_BOURNE_BY = prop.getProperty("COURIER_COST_BOURNE_BY");
		VENDOR_ENABLED = prop.getProperty("VENDOR_ENABLED");
		SHIPPING_GROUP = prop.getProperty("SHIPPING_GROUP");
		SERVICIBILITY_INDEX = prop.getProperty("SERVICIBILITY_INDEX");
		
		
		

	}

}
