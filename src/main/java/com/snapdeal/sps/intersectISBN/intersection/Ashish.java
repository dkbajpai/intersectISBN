package com.snapdeal.sps.intersectISBN.intersection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.snapdeal.sps.intersectISBN.db.ConnectionManager;
import com.snapdeal.sps.intersectISBN.utils.GeneralUtils;

public class Ashish {

	private static Map<String, String> getActiveIsbns() {

		Connection connection = ConnectionManager.getDWHConnection();
		Map<String, String> isbns = new HashMap<String, String>();
		try {
			Statement statement = connection.createStatement();
			String queryString = "select vendor_sku, GROUP_CONCAT(vendor_code) from snapdeal_ipms_dwh.vendor_inventory_pricing where supc in (select supc from cams_dwh.product where brand_id = '39954' ) and created >= '2015-01-01' group by vendor_sku";
			System.out.println(queryString);
			ResultSet resultSet = statement.executeQuery(queryString);

			while (resultSet.next())
				isbns.put(
						resultSet.getString(1).toLowerCase().replace(" ", ""),
						resultSet.getString(2).toLowerCase().replace(" ", ""));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception in dwh query");
		}
		ConnectionManager.closeConnection(connection);
		return isbns;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
	
		
		String x = "sdasd@#$$%fdgdg5656$½¿ï";
		System.out.println(GeneralUtils.removeSpecialCharacter(x));
	}

}
