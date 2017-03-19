package com.wc.blog.dao;

import java.util.LinkedList;
import java.util.List;

import com.wc.blog.bean.Blog;
import com.wc.blog.bean.Draft;

public class DraftDao {
	public static List<Draft> queryAllDrafts() {
		
		List<Draft> list = new LinkedList<Draft>();

		
		List<Blog> blogList = BlogDao.queryAllBlogs();
		
		for(Blog blog : blogList) {
			Draft d = new Draft();
			d.setId(blog.getId());
			d.setTitle(blog.getTitle());
			d.setLastModifyTime(blog.getLastModifyTime());
			d.setPublished(blog.getPublished());
			list.add(d);
		}
		
		return list;
	}
	
	
	public static void saveDraft(String name, Blog blog) {
		BlogDao.saveBlog(name, blog);
		return;
	}
	
	public static void deleteDraft(int id) {
		BlogDao.deleteBlog(id);
		return;
	}
	
	
	public static void updateDraft(int id, Blog blog) {
		BlogDao.updateBlog(id, blog);
		return;
	}
}
