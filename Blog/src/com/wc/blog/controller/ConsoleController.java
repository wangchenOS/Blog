package com.wc.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		/*
		 * User login_user =
		 * (User)request.getSession().getAttribute("login-user"); if(login_user
		 * == null){ // 说明用户没有登录，让他跳转到登录页面 request.setAttribute("error",
		 * "请登录！");
		 * //request.getRequestDispatcher("/login").forward(request,response);
		 * response.sendRedirect("/Blog/login.jsp"); // 这个return很重要！ return; }
		 * response.sendRedirect("/Blog/console.jsp");
		 */
/*
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		
		System.out.println("type:" + type + "id:" + id);*/
		/*
		 * String content = request.getParameter("content"); String tag =
		 * request.getParameter("tag");
		 */
		/*if (type.equals("draft")) {
			List<Draft> list = DraftDao.queryAllDrafts();
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			String str = gson.toJson(list);
			JsonObject obj = new JsonObject();
			obj.addProperty("result", str);
			System.out.println(obj.toString());
			try {
				// Write some content
				out.println(str);
			} finally {
				out.close();
			}
		}
*/
	/*	if (Integer.valueOf(id) != 0) {
			int blogId = Integer.valueOf(id);
			PrintWriter out = response.getWriter();
			System.out.println("blogId:" + blogId);
				Blog blog = BlogDao.queryOneBlog(blogId);
				// Write some content
				request.getSession().setAttribute("blog", blog);
				response.sendRedirect("/Blog/blog.jsp?id=" + blogId);
				//request.getRequestDispatcher("/blog.jsp").forward(request, response);
				//out.println("OK");
		}
*/
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

		System.out.println(title);
		System.out.println(content);
		System.out.println(tag);
		System.out.println(id);

		blog.setTitle(title);
		blog.setContent(content);
		blog.setTag(tag);

		HttpSession session = request.getSession();
		String name = ((User) session.getAttribute("login-user")).getName();
		int index = BlogDao.saveBlog(name, blog);
		System.out.println("index:"+index);
		PrintWriter out = response.getWriter();
		out.println(index);
		//response.sendRedirect(request.getContextPath()+"/blogShow?id=" + index);
	}
}
