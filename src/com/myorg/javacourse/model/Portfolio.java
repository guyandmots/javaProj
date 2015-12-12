package com.myorg.javacourse.model;
import com.myorg.javacourse.model.Stock;
/**
 * An instance of this class represents a portfolio of stocks.
 * @author Guy Naamati & Amots Mor
 *
 */
public class Portfolio {
	
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize;
	
	/**
	 * A constructor that builds an empty portfolio
	 */
	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
	}
	
	/**
	 * A copy constructor for the portfolio constructor.
	 * @param portfolio is the original instance to copy from.
	 */
	public Portfolio(Portfolio portfolio){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for (int i = 0; i < portfolio.portfolioSize; i++){
			this.stocks[i] = new Stock(portfolio.stocks[i]);
		}
		this.portfolioSize = portfolio.portfolioSize;
		this.title = portfolio.title;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setPortfolioTitle(String title){
		this.title=  title;
	}
	/**
	 * Add a new stock to the portfolio, if it still has place.
	 * @param stock is the stock we want to add.
	 */
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
	
	/**
	 * Removes the first stock that was added to portfolio.
	 * @author Guy Naamati
	 */
	public void removeFirstStock(){
		for (int i = 1; i < this.portfolioSize; i++){
			this.stocks[i-1] = this.stocks[i];
		}
		this.stocks[this.portfolioSize - 1] = null;
		this.portfolioSize--;
	}
	
	/**
	 * Changes the bid of the last stock that was added to portfolio.
	 * @param bid is the new value of the bid.
	 * @author Amots Mor.
	 */
	public void changeBidOfLastStock(float bid){
		this.stocks[this.portfolioSize - 1].setBid(bid);
	}
	
	public String getHtmlString(){
		String portfolioString = new String("<h1> Portfolio title: </h1>"+ "<br>" + this.title + "<br>");
		for (int i = 0; i < this.portfolioSize; i++)
		portfolioString += stocks[i].getHtmlDescription() + "<br>";
		return portfolioString;
	}
	
}
