package com.myorg.javacourse.servlet;
import java.io.IOException;
import com.myorg.javacourse.service.PortfolioManager;
import com.myorg.javacourse.model.Portfolio;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Portfolioservlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio1.getHtmlString());
		
		Portfolio portfolio2 = new Portfolio(portfolio1);
		portfolio2.setPortfolioTitle("Portfolio #2");
		resp.getWriter().println(portfolio2.getHtmlString());
		
	}
}

