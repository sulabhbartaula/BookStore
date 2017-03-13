package com.bm.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	private static String URL = "jdbc:mysql://localhost/userdb";
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String USER = "root";
	private static String PASS = "";

	private static Connection conn = null;
	
	public static Connection getConnection(){
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PASS);
			System.out.println("Connection Sucessful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
