package com.myorg.javacourse;
import java.io.IOException;
import java.util.Calendar;
import com.myorg.javacourse.Stock;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		Stock stock1, stock2,stock3;
		Calendar c = Calendar.getInstance();
		c.set(2014,  10,  15);
		 stock1 = new Stock ("PIH", 13.1f, 12.4f,c.getTime());
		 stock2 = new Stock ("AAL", 5.78f, 5.5f, c.getTime());
		stock3 = new Stock ("CAAS", 32.2f, 31.5f, c.getTime());

	
		resp.getWriter().println(stock1.getHtmlDescription()+"<br>");
		resp.getWriter().println(stock2.getHtmlDescription()+"<br>");
		resp.getWriter().println(stock3.getHtmlDescription()+"<br>");
	}
}

