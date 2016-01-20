package com.snapdeal.sps.intersectISBN.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;

public class ConnectionManager {

	
	public static Connection getLocalConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Constants.LOCAL_DB_URL,
					Constants.LOCAL_DB_USER, Constants.LOCAL_DB_PASS);
		} catch (ClassNotFoundException ex) {

			System.out.println("Class Not Found Exception -->" + ex);
		} catch (SQLException e) {
			System.out.println("SQL Connection Failed! ");
			e.printStackTrace();

		}

		return connection;
	}

	public static Connection getDWHConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Constants.DWH_DB_URL,
					Constants.DWH_DB_USER, Constants.DWH_DB_PASS);
		} catch (ClassNotFoundException ex) {

			System.out.println("Class Not Found Exception -->" + ex);
		} catch (SQLException e) {
			System.out.println("SQL Connection Failed! ");
			e.printStackTrace();

		}

		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
