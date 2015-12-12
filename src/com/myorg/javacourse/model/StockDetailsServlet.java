package com.myorg.javacourse.model;
import java.io.IOException;
import java.util.Calendar;
import com.myorg.javacourse.model.Stock;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		Calendar c = Calendar.getInstance();
		c.set(2014,  10,  15);
		Stock stock1 = new Stock("PIH", 12.4f, 13.1f, c.getTime());
		Stock stock2 = new Stock("AAL", 5.78f, 5.5f, c.getTime());
		Stock stock3 = new Stock("CAAS", 32.2f, 31.5f, c.getTime());
		resp.getWriter().println(stock1.getHtmlDescription()+"<br>");
		resp.getWriter().println(stock2.getHtmlDescription()+"<br>");
		resp.getWriter().println(stock3.getHtmlDescription()+"<br>");
	}
}
