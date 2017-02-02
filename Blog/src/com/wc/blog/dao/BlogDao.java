package com.wc.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wc.blog.bean.Blog;

public class BlogDao {
	public static void saveBlog(String name, Blog blog) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
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
				int user_id = rs.getInt("id");
				sql = "INSERT INTO Blog (title,content,file,tag,user_id) " +
		               "VALUES (?, ?, ?, ?, ?)"; 
				pst = c.prepareStatement(sql);
				pst.setString(1, blog.getTitle());
				pst.setString(2, blog.getContent());
				pst.setString(3, blog.getFile());
				pst.setString(4, blog.getTag());
				pst.setInt(5, user_id);
				
				int result = pst.executeUpdate();
			    c.commit();
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
		return;
	}
	
	
	public static void readBlog(String name) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
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
				int user_id = rs.getInt("id");
				String sql1 = "select * from Blog where user_id = ? "; 
				PreparedStatement pst1 = c.prepareStatement(sql1);
				pst1.setInt(1, user_id);
				
				ResultSet rs1 = pst1.executeQuery();
				String content = rs1.getString("content");
				System.out.println(content);
				
				byte[] val = rs1.getBytes("content"); 
				String tr=new String(val);
				System.out.println(tr);
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
				System.out.println("222");
			}
			SQLiteJDBC.closeConnection(c);

		}
		return;
	}
	
	public static void main(String[] args) {
		readBlog("wc");
	}
}
