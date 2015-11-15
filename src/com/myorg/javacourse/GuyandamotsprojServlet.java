package com.myorg.javacourse;

import java.io.IOException;
import java.lang.Math;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GuyandamotsprojServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		resp.getWriter().println("Hello , world");

		int value = Math.abs(-1);
		resp.getWriter().println(value);
	}
}
