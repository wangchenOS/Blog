package com.wc.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wc.blog.bean.Blog;
import com.wc.blog.bean.User;
import com.wc.blog.dao.BlogDao;

/**
 * Servlet implementation class BlogController
 */
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		User login_user = (User)request.getSession().getAttribute("login-user");
		if(login_user == null){
		    // 说明用户没有登录，让他跳转到登录页面
		    request.setAttribute("error", "请登录！");
		    //request.getRequestDispatcher("/login").forward(request,response);
		    response.sendRedirect("/Blog/login.jsp");
		    // 这个return很重要！
		    return;
		}
		response.sendRedirect("/Blog/console.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Blog blog = new Blog();
		request.setCharacterEncoding("UTF-8");//传值编码
        response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String title = request.getParameter("tile");
		String content = request.getParameter("content");
		String tag = request.getParameter("tag");
		
		System.out.println(title);
		System.out.println(content);
		System.out.println(tag);
		
		blog.setTitle(title);
		blog.setContent(content);
		blog.setTag(tag);
		
		HttpSession session = request.getSession();
		String name = ((User)session.getAttribute("login-user")).getName();
		BlogDao.saveBlog(name, blog);
	}

}
