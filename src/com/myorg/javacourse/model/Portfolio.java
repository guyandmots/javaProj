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
	private StockInterface[] stocks;
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
			this.stocks[i] = new Stock((Stock) portfolio.stocks[i]);
		}
		this.portfolioSize = portfolio.portfolioSize;
		this.title = portfolio.title;
	}
	

	public Portfolio(Stock[] stockArray) {
		for (int i = 0; i < stockArray.length; i++){
			this.stocks[i] = new Stock(stockArray[i]);
	}
		this.portfolioSize=stockArray.length;
	}

	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	
	
	public String getTitle(){
		return this.title;
	}
	public float getStocksValue() {
		int totalStocksValue = 0;
		for (int i = 0; i < portfolioSize; i++) {
			totalStocksValue += this.stocks[i].getBid() * ((Stock) this.stocks[i]).getQuantity();
		}
		return totalStocksValue;
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

	public float getTotalValue() {
		return getStocksValue() + getBalance();
	}

	
	public void setTitle(String title){
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
				((Stock) stocks[portfolioSize++]).setQuantity(0);;
				return portfolioSize -1;
			}
			else {
				return i;
			}
		}
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
	public void removeStock(String symbol){
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
			//exception
		while (i < portfolioSize - 1){
			stocks[i] = stocks[i + 1];
			i++;
		}
		stocks[portfolioSize--] = null;
	}
	
	/**
	 * Sells stocks from portfolio.
	 * @param symbol is the symbol of the stock we want to sell.
	 * @param quantity is the quantity of stocks to sell, if there are enough of it to sell.
	 * @return a boolean value, true if selling was succeeded, false if not.
	 */
	public void sellStock (String symbol, int quantity){
		
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)){
				if (quantity == -1){
					this.updateBalance(stocks[i].getBid() * ((Stock) stocks[i]).getQuantity());
					((Stock) stocks[i]).setQuantity(0);;;
				}
				else if (quantity > ((Stock) stocks[i]).getQuantity()){
					System.out.println("Not enough stocks to sell!");
				}
				else{
					this.updateBalance(stocks[i].getBid() * quantity);
					((Stock) stocks[i]).setQuantity(((Stock) stocks[i]).getQuantity() - quantity);
				}
			}
		}
	}

	/**
	 * Buys stocks, if it is enough balance in the portfolio for it.
	 * @param stock is the stock we want to buy.
	 * @param quantity is the quantity of stocks to buy.
	 * @return a boolean value- true if buying was succeeded, false if not.
	 */
	public void buyStock (Stock stock, int quantity) {
		if (stock.getAsk() * quantity > this.getBalance()) {
			System.out.println("Not enough balance to complete purchase");
		}
		int stockLocation = addStock(stock);
		if (stockLocation == -1) {
			//exception
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
	}


	/**
	 * Changes the bid of the last stock that was added to portfolio.
	 * @param bid is the new value of the bid.
	 * @author Amots Mor.
	 */
	public void changeBidOfLastStock(float bid){
		((Stock) this.stocks[this.portfolioSize - 1]).setBid(bid);
	}
	

	public String getHtmlString(){
		String portfolioString = new String("<h1>" + this.title + "</h1>");
		for (int i = 0; i < this.portfolioSize; i++) {
			portfolioString += ((Stock) stocks[i]).getHtmlDescription() + "<br>";
		}
		portfolioString += "Total portfolio value: " + getTotalValue() + "<br> Total stocks value: " 
		+ getStocksValue() + "<br> Balance: " + getBalance();
		return portfolioString;
	}

	public Stock findStock(String symbol) {
		for (int i = 0; i < portfolioSize; i++) 
			if (symbol.equals(stocks[i].getSymbol()))
				return (Stock) stocks[i];
		return null;
		
		
	}
}

	

	


	


