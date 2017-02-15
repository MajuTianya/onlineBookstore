package cn.maju.goods.author.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.maju.goods.author.service.AuthorService;

public class AuthorServlet extends HttpServlet {
	AuthorService authorService=new AuthorService();

	public String done(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return null;
	}

}
