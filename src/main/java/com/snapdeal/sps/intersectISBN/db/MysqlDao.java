package com.snapdeal.sps.intersectISBN.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class MysqlDao {

	
	
	/*
	 * static Connection connection;
	 * static {
		connection = ConnectionManager.getDWHConnection();
	}*/
	
	private static Set<String> getDisabledIsbns(Connection connection){
		Set<String> isbns = new HashSet<String>();
		try{
			Statement statement = connection.createStatement();
			String queryString = "select vendor_sku from snapdeal_ipms_dwh.vendor_inventory_pricing where supc in (select supc from cams_dwh.product where brand_id = '39954' ) and created < '2015-01-01'";
			System.out.println(queryString);
			ResultSet resultSet = statement.executeQuery(queryString);

			while(resultSet.next())
				isbns.add(resultSet.getString(1).toLowerCase().replace(" ", ""));


		}catch(Exception e){
			e.printStackTrace();
			System.out.println("exception in local query");
		}
		return isbns;	
	}


	private static Set<String> getActiveIsbns(Connection connection){
		Set<String> isbns = new HashSet<String>();
		try{
			Statement statement = connection.createStatement();
			String queryString = "select vendor_sku from snapdeal_ipms_dwh.vendor_inventory_pricing where supc in (select supc from cams_dwh.product where brand_id = '39954' ) and created >= '2015-01-01'";
			System.out.println(queryString);
			ResultSet resultSet = statement.executeQuery(queryString);

			while(resultSet.next())
				isbns.add(resultSet.getString(1).toLowerCase().replace(" ", ""));


		}catch(Exception e){
			e.printStackTrace();
			System.out.println("exception in dwh query");
		}
		return isbns;	
	}
	
	private static Set<String> getProcessedIsbns(Connection connection){
		Set<String> isbns = new HashSet<String>();
		try{
			Statement statement = connection.createStatement();
			String queryString = "select vendor_sku from snapdeal_ipms_dwh.vendor_inventory_pricing where vendor_code = 'S940db' and created >= '2016-01-15'";
			System.out.println(queryString);
			ResultSet resultSet = statement.executeQuery(queryString);

			while(resultSet.next())
				isbns.add(resultSet.getString(1).toLowerCase().replace(" ", ""));

		}catch(Exception e){
			e.printStackTrace();
			System.out.println("exception in dwh query");
		}
		return isbns;	
	}

	public static Set<String> getActiveIsbns(){

		Connection connection = ConnectionManager.getDWHConnection();
		Set<String> activeIsbns = getActiveIsbns(connection);
		ConnectionManager.closeConnection(connection);
		return activeIsbns;
	}


	public static Set<String> getDisabledIsbns(){

		Connection connection = ConnectionManager.getDWHConnection();
		Set<String> disabledIsbns = getDisabledIsbns(connection);
		ConnectionManager.closeConnection(connection);
		return disabledIsbns;
	}
	
	public static Set<String> getProcessedIsbns(){

		Connection connection = ConnectionManager.getDWHConnection();
		Set<String> processedIsbns = getDisabledIsbns(connection);
		ConnectionManager.closeConnection(connection);
		return processedIsbns;
	}


}
