package com.wc.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.wc.blog.bean.Blog;
import com.wc.blog.bean.Draft;
import com.wc.blog.bean.PageModel;

public class BlogDao {
	public static int saveBlog(String name, Blog blog) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int returnID= -1;
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
				sql = "INSERT INTO Blog (title,content,file,tag,user_id, published, publishTime) " +
		               "VALUES (?, ?, ?, ?, ?, ?, ?)"; 
				pst = c.prepareStatement(sql);
				pst.setString(1, blog.getTitle());
				pst.setString(2, blog.getContent());
				pst.setString(3, blog.getFile());
				pst.setString(4, blog.getTag());
				pst.setInt(5, user_id);
				pst.setInt(6, blog.getPublished());
				pst.setString(7, blog.getPublishTime());
				
				pst.executeUpdate();
				
			  
				c.commit();
			    
			}
			
			 sql = "select last_insert_rowid() from Blog";
			 pst = c.prepareStatement(sql);
			 rs = pst.executeQuery(); //获取结果   
			 if (rs.next()) {
				    returnID = rs.getInt(1);//取得ID
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
		return returnID;
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

			String sql = "update Blog set title = ?, content = ?, file = ?, tag = ?, published = ?, publishTime = ? where id = ?";
			pst = c.prepareStatement(sql);
			pst.setString(1, blog.getTitle());
			pst.setString(2, blog.getContent());
			pst.setString(3, blog.getFile());
			pst.setString(4, blog.getTag());
			pst.setInt(5, blog.getPublished());
			pst.setString(6, blog.getPublishTime());
			pst.setInt(7, id);
			
			pst.executeUpdate();
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
	
	public static Blog queryOneBlog(int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		//Blog blog = new Blog();
		Blog blog = new Blog();
		try {
			c = SQLiteJDBC.openConnection();
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "select * from Blog where id = ?";  
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				String title = rs.getString("title");
				blog.setTitle(title);
				
				String content = rs.getString("content");
				blog.setContent(content);
				
				String file = rs.getString("file");
				blog.setFile(file);
				
				String tag = rs.getString("tag");
				blog.setTag(tag );
				
				String publishTime = rs.getString("publishTime");
				blog.setPublishTime(publishTime);;
				
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
		
		blog.setId(id);
		return blog;
	}
	
	
	  public static PageModel findBlogs(int pageNo,int pageSize){  
		  Connection conn = null;
		  ResultSet rs = null;
		  
		  
		  ResultSet rs2 = null;
			
          String sql="select * from Blog limit ?,?";  
          PageModel pageModel=null;  
          PreparedStatement pstm=null;  
          Blog blog=null;  
          List<Blog> list=new ArrayList<Blog>();  
          try {  
        	  conn = SQLiteJDBC.openConnection();
        	  
              pstm=conn.prepareStatement(sql);  
              pstm.setInt(1, (pageNo-1)*pageSize);  
              pstm.setInt(2, pageSize);  
              System.out.println("limit from " + (pageNo-1)*pageSize + " to " + pageNo*pageSize);
              rs=pstm.executeQuery();
              while(rs.next()){  
            	  blog=new Blog();  
            	 
            	  int id = rs.getInt("id");
    				blog.setId(id);
    				
            	  String title = rs.getString("title");
  				blog.setTitle(title);
  				
  				String content = rs.getString("content");
  				
  				
  				String summary = occurTimes(content, "\n");
  				System.out.println("Time is " + summary);
  				blog.setContent(summary);
  				
  				String file = rs.getString("file");
  				blog.setFile(file);
  				
  				String tag = rs.getString("tag");
  				blog.setTag(tag );
  				
  				String publishTime = rs.getString("publishTime");
  				blog.setPublishTime(publishTime);;
                  
  				list.add(blog);  
              }  
              
              Statement stat = conn.createStatement(); 
              rs2=stat.executeQuery("select count(*) from Blog;");  
              int total=0;  
              if(rs2.next()){  
                  total=rs2.getInt(1);  
              } 
              pageModel=new PageModel();  
              pageModel.setPageNo(pageNo);  
              pageModel.setPageSize(pageSize);  
              pageModel.setTotalRecords(total);  
              pageModel.setList(list);  
          } catch (SQLException e) {  
              e.printStackTrace();  
          }finally{  
        	  try {
  				if (rs != null) {
  					rs.close();
  				}
  				if (rs2 != null) {
  					rs2.close();
  				}
  				if (pstm != null) {
  					pstm.close();
  				}
  			} catch (SQLException e) {
  				System.out.println("SQLException");
  			}
  			SQLiteJDBC.closeConnection(conn);
          }  
          return pageModel;  
	  }
	  
	  public static String occurTimes(String string, String a) {
		    int pos = -2;
		    int n = 0;
		 
		    while (pos != -1) {
		        if (pos == -2) {
		            pos = -1;
		        }
		        pos = string.indexOf(a, pos + 1);
		        if (pos != -1) {
		            n++;
		        }
		        
		        if (n == 4) {
		        	return string.substring(0, pos);
		        }
		    }
		    
		    return string;
		}
	  
	public static void main(String[] args) {
		//readBlog("wc");
		PageModel pageModel=findBlogs(1,5);
		 System.out.print("当前页:"+pageModel.getPageNo()+" ");  
         System.out.print("共"+pageModel.getTotalPages()+"页  ");  
         System.out.print("首页:"+pageModel.getTopPageNo()+" ");  
         System.out.print("上一页:"+pageModel.getPreviousPageNo()+" ");  
         System.out.print("下一页:"+pageModel.getNextPageNo()+" ");  
         System.out.print("尾页:"+pageModel.getBottomPageNo()+" ");  
         System.out.print("共"+pageModel.getTotalRecords()+"条记录"); 
	}
}
