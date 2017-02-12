package com.wc.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wc.blog.bean.User;
import com.wc.blog.dao.UserDao;

/**
 * Servlet implementation class Servlet
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("/Blog/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String name = request.getParameter("inputName");
		String password = request.getParameter("inputPassword");
		System.out.println(name+ ":" + password);
		boolean flag = UserDao.checkUser(name, password);
		System.out.println(flag);
		if (flag) {
			HttpSession session = request.getSession();
			User user = new User();
			user.setName(name);
			user.setPasswd(password);
			session.setAttribute("login-user", user);
			//request.getRequestDispatcher("/BlogController").forward(request,response);
			response.sendRedirect("/Blog/console.jsp");
			return;
		}else {
			response.sendRedirect("/Blog/login.jsp");
		}
	}

}
