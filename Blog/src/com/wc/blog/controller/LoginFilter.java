package com.wc.blog.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	public static final String login_page = "/Blog/login.jsp";
	//public static final String logout_page = "/test/admin/Public/login.jsp";

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getRequestURI();
		String ctxPath = request.getContextPath();
		// 除掉项目名称时访问页面当前路径
		String targetURL = currentURL.substring(ctxPath.length());
		HttpSession session = request.getSession(false);
		// 对当前页面进行判断，如果当前页面不为登录页面
		if (!("/login.jsp".equals(targetURL))) {
			System.out.println("TargetUrl: " + targetURL + " | CtxPath:" + ctxPath + " | CurrentURL:" + currentURL);
			// 在不为登陆页面时，再进行判断，如果不是登陆页面也没有session则跳转到登录页面，
			if (session == null || session.getAttribute("login-user") == null) {
				response.sendRedirect(login_page);
				return;
			} else {
				// 这里表示正确，会去寻找下一个链，如果不存在，则进行正常的页面跳转
				chain.doFilter(request, response);
				return;
			}
		} else {
			// 这里表示如果当前页面是登陆页面，跳转到登陆页面
			chain.doFilter(request, response);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
