package com.wc.blog.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageController
 */
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_PATH = "D:/";
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		UUID uuid = UUID.randomUUID();
    	System.out.println("uuid : " + uuid);
    	
    	String path = this.getServletContext().getRealPath("/storeImages");
    	System.out.println(path);
    	String filePath = path + "\\" + uuid + ".png";
		//利用request对象返回客户端来的输入流  
        try (ServletInputStream sis = request.getInputStream()) {  
        	
            OutputStream os = new FileOutputStream(filePath);  
            BufferedOutputStream bos = new BufferedOutputStream(os);  
              
            byte[] buf= new byte[1024];  
            int length = 0;  
            length = sis.readLine(buf, 0, buf.length);//使用sis的读取数据的方法  
            while(length!=-1) {  
                bos.write(buf, 0, length);  
                length = sis.read(buf);  
            }  
            sis.close();  
            bos.close();  
            os.close();  
        }  
        
        StringBuffer url = request.getRequestURL();
        int i = url.indexOf("/Blog/");
        String urlReturn = url.substring(0, i);
        urlReturn += "/Blog/storeImages/" + uuid + ".png";
	       //System.out.println(url1);
        out.println(urlReturn);
	}

	private  String getPath() {
		String path = this.getServletContext().getRealPath("/");
		return path;
	}
	
	public static void main(String[] args) {
		StringBuffer url = new StringBuffer("http://localhost:8080/Blog/console.jsp");
		   int i = url.indexOf("/Blog/");
		   
		   String url1 = url.substring(0, i);
		   url1 += "/Blog/image";
	       System.out.println(url1);
		   //out.println(filePath);
		
	}
}
