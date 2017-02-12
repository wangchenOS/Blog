package com.wc.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.wc.blog.bean.Blog;
import com.wc.blog.bean.Draft;

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
	
	
	public static void deleteBlog(int id) {
		Connection c = null;
		PreparedStatement pst = null;
		//int rs = 0;
		
		List<Draft> list = new LinkedList<Draft>();
		try {
			c = SQLiteJDBC.openConnection();
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "delete from Blog where id = ?";  
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			c.commit();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException");
			}
			SQLiteJDBC.closeConnection(c);

		}
	}
	
	public static void updateBlog(int id, Blog blog) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = SQLiteJDBC.openConnection();
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "update Blog set title = ?, content = ?, file = ?, tag = ? where id = ?";
			pst = c.prepareStatement(sql);
			pst.setString(1, blog.getTitle());
			pst.setString(2, blog.getContent());
			pst.setString(3, blog.getFile());
			pst.setString(4, blog.getTag());
			pst.setInt(5, id);
			c.commit();
			
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
	
	
	public static List<Blog> queryAllBlogs() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<Blog> list = new LinkedList<Blog>();
		try {
			c = SQLiteJDBC.openConnection();
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "select * from Blog";  
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Blog d = new Blog();
				
				int id = rs.getInt("id");
				d.setId(id);
				
				String title = rs.getString("title");
				d.setTitle(title);
				
				String content = rs.getString("content");
				d.setContent(content);
				
				String file = rs.getString("file");
				d.setFile(file);
				
				String tag = rs.getString("tag");
				d.setTag(tag );
				
				list.add(d);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException");
			}
			SQLiteJDBC.closeConnection(c);

		}
		return list;
	}
	public static void main(String[] args) {
		readBlog("wc");
	}
}
