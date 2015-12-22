package com.myorg.javacourse.model;
import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import com.myorg.javacourse.model.Stock;
/**
 * An instance of this class represents a portfolio of stocks.
 * @author Guy Naamati & Amots Mor
 *
 */
public class Portfolio implements PortfolioInterface {
	
    public enum ALGO_RECOMMENDATION {BUY, SELL, REMOVE, HOLD};
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private int portfolioSize;

	private float balance;
	
	/**
	 * A constructor that builds an empty portfolio
	 */
	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
	}
	
	/**
	 * A constructor that builds an empty portfolio
	 */
	public Portfolio(String title){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
		this.title = title;
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
	
	public Portfolio(Stock[] stockArray) {
		// TODO Auto-generated constructor stub
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
	/**
	 * Adds stock to portfolio, if it still have place.
	 * @param stock is the stock we want to add.
	 * @return If the stock is already in the portfolio, returns the place its appears. If the portfolio is full, returns -1.
	 */
	public int addStock(Stock stock){
		if(portfolioSize == MAX_PORTFOLIO_SIZE) {
			System.out.print("Can't add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks!");
			return -1;
		}
		else{
			boolean stockAlreadyExists = false;
			int i;
			for (i = 0; i < portfolioSize; i++){
				if (stocks[i].getSymbol().equals(stock.getSymbol())){
					stockAlreadyExists = true;
					break;
				}
			}
			if (stockAlreadyExists == false){
				stocks[portfolioSize] = stock;
				stocks[portfolioSize++].setQuantity(0);;
				return portfolioSize -1;
			}
			else {
				return i;
			}
		}
	}

	public StockInterface[] getStocks(){
		return stocks;
	}
	
	public float getBalance() {
		return balance;
	}
		
	public int getPortfolioSize(){
		return portfolioSize;
	}

	/**
	 * Increasing or decreasing the balance. balance can't be negative.
	 * @param amount is the amount to add/reduce from the balance.
	 */
	public void updateBalance (float amount){
		if (amount > 0)
			balance += amount;
		else{
			if (Math.abs(amount) < balance)
				balance += amount;
			else
				System.out.println("Balance can't be negative!");
		}
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
	 * Removes a stock completely from portfolio & sell all of it's quantity.
	 * @param symbol is the symbol of the stock which the method removes.
	 * @return a boolean value, true if removing was succeeded and false if not.
	 */
	public boolean removeStock(String symbol){
		sellStock(symbol, -1);
		int i;
		boolean flag = false;
		for (i = 0; i < portfolioSize; i++){
			if (stocks[i].getSymbol().equals(symbol)){
				flag = true;
				break;
			}
		}
		if (!flag)
			return false;
		while (i < portfolioSize - 1){
			stocks[i] = stocks[i + 1];
			i++;
		}
		stocks[portfolioSize--] = null;
		return true;
	}
	
	/**
	 * Sells stocks from portfolio.
	 * @param symbol is the symbol of the stock we want to sell.
	 * @param quantity is the quantity of stocks to sell, if there are enough of it to sell.
	 * @return a boolean value, true if selling was succeeded, false if not.
	 */
	public boolean sellStock (String symbol, int quantity){
		boolean flag = false;
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)){
				flag = true;
				if (quantity == -1){
					this.updateBalance(stocks[i].getBid() * stocks[i].getQuantity());
					stocks[i].setQuantity(0);;;
				}
				else if (quantity > stocks[i].getQuantity()){
					System.out.println("Not enough stocks to sell!");
					flag = false;
				}
				else{
					this.updateBalance(stocks[i].getBid() * quantity);
					stocks[i].setQuantity(stocks[i].getQuantity() - quantity);
				}
			}
		}
		return flag;
	}

	/**
	 * Buys stocks, if it is enough balance in the portfolio for it.
	 * @param stock is the stock we want to buy.
	 * @param quantity is the quantity of stocks to buy.
	 * @return a boolean value- true if buying was succeeded, false if not.
	 */
	public boolean buyStock (Stock stock, int quantity) {
		if (stock.getAsk() * quantity > this.getBalance()) {
			System.out.println("Not enough balance to complete purchase");
			return false;
		}
		int stockLocation = addStock(stock);
		if (stockLocation == -1) {
			return false;
		}
		else {
			if (quantity == -1) {
				int possibleQuantity = (int) (this.getBalance() / stock.getAsk());
				stock.setQuantity(stock.getQuantity() + possibleQuantity);
				this.updateBalance(-(possibleQuantity * stock.getAsk()));
			}
			else {
				stock.setQuantity(stock.getQuantity() + quantity);
				this.updateBalance(-(quantity * stock.getAsk()));
			}
		}
		return true;
	}


	/**
	 * Changes the bid of the last stock that was added to portfolio.
	 * @param bid is the new value of the bid.
	 * @author Amots Mor.
	 */
	public void changeBidOfLastStock(float bid){
		this.stocks[this.portfolioSize - 1].setBid(bid);
	}
	
	public float getStocksValue() {
		int totalStocksValue = 0;
		for (int i = 0; i < portfolioSize; i++) {
			totalStocksValue += this.stocks[i].getBid() * this.stocks[i].getQuantity();
		}
		return totalStocksValue;
	}
	

	public float getTotalValue() {
		return getStocksValue() + getBalance();
	}

	public String getHtmlString(){
		String portfolioString = new String("<h1>" + this.title + "</h1>");
		for (int i = 0; i < this.portfolioSize; i++) {
			portfolioString += stocks[i].getHtmlDescription() + "<br>";
		}
		portfolioString += "Total portfolio value: " + getTotalValue() + "<br> Total stocks value: " 
		+ getStocksValue() + "<br> Balance: " + getBalance();
		return portfolioString;
	}



	
}

