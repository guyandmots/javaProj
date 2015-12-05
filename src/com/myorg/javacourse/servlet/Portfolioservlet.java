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
		Portfolio portfolio = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio.getHtmlString());
	}
}

