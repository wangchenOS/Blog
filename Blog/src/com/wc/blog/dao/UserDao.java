package com.wc.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public static boolean checkUser(String name, String password) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			c = SQLiteJDBC.openConnection();
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "select * from User where name = ?";  // ���в���
			pst = c.prepareStatement(sql);
			pst.setString(1, name);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				// int id = rs.getInt("id");
				String passwd = rs.getString("password");
				if(password.equals(passwd)) {
					flag = true;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				System.out.println("");
			}
			SQLiteJDBC.closeConnection(c);

		}
		return flag;
	}
	
	
	public static int queryUserId(String name) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id = -1;
		try {
			c = SQLiteJDBC.openConnection();
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "select * from User where name = ?";
			pst = c.prepareStatement(sql);
			pst.setString(1, name);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				// int id = rs.getInt("id");
				id = rs.getInt("id");
				 
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL ERROR");
			}
			SQLiteJDBC.closeConnection(c);

		}
		return id;
	}
}
