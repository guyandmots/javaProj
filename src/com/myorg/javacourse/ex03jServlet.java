package com.myorg.javacourse;

import java.io.IOException;
import javax.servlet.http.*;
import com.myorg.javacourse.MathJa;

@SuppressWarnings("serial")
public class ex03jServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		MathJa mathja= new MathJa(50, 30, 50, 2,3 );
		
		
		String resultStr=mathja.getResults();
		
		resp.getWriter().println("Results: "+"<br>"+ resultStr);
	}
}
