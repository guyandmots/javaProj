package com.myorg.javacourse.service;
import java.util.Calendar;
import com.myorg.javacourse.model.Stock;
import com.myorg.javacourse.model.Portfolio;

public class PortfolioManager {
	public Portfolio getPortfolio(){
		Calendar c = Calendar.getInstance();
		c.set(2014,  10,  15);
		Portfolio portfolio = new Portfolio();
		Stock stock1 = new Stock("PIH", 12.4f, 13.1f, c.getTime());
		Stock stock2 = new Stock("AAL", 5.78f, 5.5f, c.getTime());
		Stock stock3 = new Stock("CAAS", 32.2f, 31.5f, c.getTime());
		portfolio.addStock(stock1);
		portfolio.addStock(stock2);
		portfolio.addStock(stock3);
		return portfolio;
	}
}
