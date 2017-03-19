package com.wc.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wc.blog.bean.Blog;
import com.wc.blog.bean.Draft;
import com.wc.blog.bean.PageModel;
import com.wc.blog.bean.User;
import com.wc.blog.dao.BlogDao;
import com.wc.blog.dao.DraftDao;

/**
 * Servlet implementation class BlogController
 */
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		request.setCharacterEncoding("UTF-8");// 传值编码
		response.setContentType("text/html;charset=UTF-8");// 设置传输编码
		String type = request.getParameter("type");
		String id = request.getParameter("id");

		System.out.println("type:" + type + " id:" + id);

		String getServletPath = request.getServletPath();
		System.out.println("getServletPath:" + getServletPath);
		/*
		 * String content = request.getParameter("content"); String tag =
		 * request.getParameter("tag");
		 */
		if (type != null && type.equals("draft")) {
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
		} else {
			if (id != null && Integer.valueOf(id) != 0) {
				int blogId = Integer.valueOf(id);
				PrintWriter out = response.getWriter();
				System.out.println("blogId:" + blogId);
				Blog blog = BlogDao.queryOneBlog(blogId);
				// Write some content
				// request.getSession().setAttribute("blog", blog);
				request.setAttribute("blog", blog);
				request.getRequestDispatcher("blog.jsp").forward(request, response);
				return;

			}
		}

		/*
		 * if (type == null ) { PageModel model = BlogDao.findBlogs(1, 5); //
		 * Write some content //request.getSession().setAttribute("blog", blog);
		 * request.setAttribute("model", model);
		 * request.getRequestDispatcher("blogList.jsp").forward(request,
		 * response); return;
		 * 
		 * }
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
		System.out.println("index:" + index);
		response.sendRedirect(request.getContextPath() + "/blog.jsp?id=" + index);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doDelete(req, resp);
		String type = req.getParameter("type");
		String id = req.getParameter("id");
		/*
		 * String content = request.getParameter("content"); String tag =
		 * request.getParameter("tag");
		 */
		if (type.equals("draft")) {
			PrintWriter out = resp.getWriter();
			System.out.println("type:" + type + "|id:" + id);
			try {
				DraftDao.deleteDraft(Integer.parseInt(id));
				// Write some content
				out.println("OK");
			} finally {
				out.close();
			}
		}
	}
}
