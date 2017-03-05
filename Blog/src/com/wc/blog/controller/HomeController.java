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
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();

	private static final int PAGE_SIZE = 5;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
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
		request.setCharacterEncoding("UTF-8");// 传值编码
		response.setContentType("text/html;charset=UTF-8");// 设置传输编码
		String id = request.getParameter("id");

		System.out.println("id:" + id);

		if (id != null && Integer.valueOf(id) != 0) {
			int pageNo = Integer.valueOf(id);
			
			PageModel model = BlogDao.findBlogs(pageNo, PAGE_SIZE);
			request.setAttribute("model", model);
			request.getRequestDispatcher("blogList.jsp").forward(request, response);
			
			return;
		}

		PageModel model = BlogDao.findBlogs(1, PAGE_SIZE);
		// Write some content
		// request.getSession().setAttribute("blog", blog);
		request.setAttribute("model", model);
		request.getRequestDispatcher("blogList.jsp").forward(request, response);
		return;

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
