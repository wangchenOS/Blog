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
import com.wc.blog.dao.DraftDao;

public class ConsoleController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ConsoleController");
		String id = request.getParameter("id");

		if (id != null) {
			System.out.println("ConsoleController id is " + id);
			Blog blog = BlogDao.queryOneBlog(Integer.valueOf(id));
			request.setAttribute("blog", blog);

		}
		// Write some content
		// request.getSession().setAttribute("blog", blog);
		request.getRequestDispatcher("/console.jsp").forward(request, response);

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
		String type = request.getParameter("type");
		String draftId = request.getParameter("draftId");

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

		int updateId = Integer.valueOf(draftId);

		int index = -1;
		if (updateId > -1) {
			blog.setId(updateId);
			BlogDao.updateBlog(updateId, blog);
			index = updateId;
			System.out.println("Update blog ");

		} else {
			index = BlogDao.saveBlog(name, blog);

			System.out.println("Save draft and the index is " + index);

		}

		if ("1".equals(type)) {
			PrintWriter out = response.getWriter();
			out.println(index);
		}
		// response.sendRedirect(request.getContextPath()+"/blogShow?id=" +
		// index);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doDelete(req, resp);
		String id = req.getParameter("id");
		/*
		 * String content = request.getParameter("content"); String tag =
		 * request.getParameter("tag");
		 */
		PrintWriter out = resp.getWriter();
		System.out.println("Delete blog id is " + id);
		
		if (id != null) {
			DraftDao.deleteDraft(Integer.parseInt(id));
		}
		
			// Write some content
			out.println("OK");
			out.close();
	}
}
