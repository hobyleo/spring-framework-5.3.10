package com.hoby.xml.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hoby
 * @since 2024-01-12
 */
@Component("/httpRequestHandler")
public class HttpRequestController implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HttpRequestController Working");
		// 为返回页面设置数据
		request.setAttribute("source", "HttpRequestController");
		// 设置返回页面
		request.getRequestDispatcher("/WEB-INF/jsp/a.jsp").forward(request, response);
	}

}
