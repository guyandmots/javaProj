package com.myorg.javacourse.model;
import com.myorg.javacourse.model.Stock;

public class Portfolio {
	
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize;
	
	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
	}
	
	public Portfolio(Portfolio portfolio){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for (int i = 0; i < portfolio.portfolioSize; i++){
			this.stocks[i] = new Stock(portfolio.stocks[i]);
		}
		this.portfolioSize = portfolio.portfolioSize;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setPortfolioTitle(String title){
		this.title=  title;
	}
	
	public void addStock(Stock stock){
		if(portfolioSize == MAX_PORTFOLIO_SIZE)
			System.out.print("Your portfolio is full!");
		else{
			stocks[portfolioSize++] = stock;
		}
	}
	
	public Stock[] getStocks(){
		return stocks;
	}
	public String getHtmlString(){
		String portfolioString = new String("<h1> Portfolio title: </h1>"+ "<br>" + this.title);
		for (int i = 0; i < this.portfolioSize; i++)
		portfolioString += stocks[i].getHtmlDescription() + "<br>";
		return portfolioString;
	}
	
}
