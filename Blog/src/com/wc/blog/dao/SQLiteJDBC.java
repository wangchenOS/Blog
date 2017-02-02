package com.wc.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.JDBC;

public class SQLiteJDBC {
	static String path1 = System.getProperty("user.dir");
	/*private static final String DB_PATH = "jdbc:sqlite://" + System.getProperty("user.dir").replace('\\', '/')
			+ "/src/sqllite/blog.db";*/
	private static final String DB_PATH = "jdbc:sqlite://C:/Users/wangchen/workspace/Blog/src/sqllite/blog.db";
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String USER = "blog";
	private static final String PASSWD = "blog";

	static {
		try {
			System.out.println(path1);
			System.out.println(DB_PATH);
			System.out.println(DRIVER);
			Class.forName(DRIVER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection openConnection() throws SQLException {
		return DriverManager.getConnection(DB_PATH, USER, PASSWD);
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Con closed");
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(DB_PATH);
			System.out.println(DRIVER);
			Class.forName(DRIVER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
