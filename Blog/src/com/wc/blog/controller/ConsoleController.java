package com.wc.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wc.blog.bean.Blog;
import com.wc.blog.bean.User;
import com.wc.blog.dao.BlogDao;

public class ConsoleController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ConsoleController");
		request.getRequestDispatcher("/console.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Blog blog = new Blog();
		request.setCharacterEncoding("UTF-8");// 传值编码
		response.setContentType("text/html;charset=UTF-8");// 设置传输编码
		String title = request.getParameter("tile");
		String content = request.getParameter("content");
		String tag = request.getParameter("tag");
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		String publishTime = dateFormat.format(now); 
		System.out.println(publishTime); 

		blog.setTitle(title);
		blog.setContent(content);
		blog.setTag(tag);
		blog.setPublishTime(publishTime);
		blog.setPublished(Byte.parseByte(type));

		HttpSession session = request.getSession();
		String name = ((User) session.getAttribute("login-user")).getName();
		int index = BlogDao.saveBlog(name, blog);
		System.out.println("index:"+index);
		PrintWriter out = response.getWriter();
		out.println(index);
		//response.sendRedirect(request.getContextPath()+"/blogShow?id=" + index);
	}
}
