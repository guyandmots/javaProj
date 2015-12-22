package com.myorg.javacourse.model;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.algo.model.StockInterface;

import com.myorg.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * An instance of this class represents a stock.
 * @author Guy Naamati & Amots mor
 *
 */
public class Stock implements StockInterface{
	
	private String symbol;
	private float ask, bid;
	private Date buyingDate;
	private ALGO_RECOMMENDATION recommendation;
	public int stockQuantity;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	/**
	 * A constructor thats builds a stock by parameters.
	 * @param symbol is the stock's symbol.
	 * @param ask is the ask value of the stock.
	 * @param bid is the bid value of the stock.
	 * @param buyingDate is the date which the stock was purchased.
	 */
	public Stock(String symbol, float ask, float bid, Date buyingDate){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.buyingDate = buyingDate;
	}

	
	/**
	 * A constructor thats builds a stock by parameters.
	 * @param symbol is the stock's symbol.
	 * @param ask is the ask value of the stock.
	 * @param bid is the bid value of the stock.
	 */

	public Stock(String symbol, float ask, float bid){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		
	}
	
	/**
	 * A copy constructor for stock.
	 * @param stock is the original instance to copy from.
	 */
	public Stock(Stock stock){
		this(stock.getSymbol(),stock.getAsk(),stock.getBid());
		this.buyingDate = new Date (stock.getDate().getTime());	
	}
	public Stock() {
	   this.setQuantity(0);
	}


	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public float getAsk() {
		return ask;
	}
	
	public void setAsk(float ask) {
		this.ask = ask;
	}
	
	public float getBid() {
		return bid;
	}
	public int getStockQuantity(){
		return stockQuantity;
	}
	
	public void setBid(float bid) {
		this.bid = bid;
	}
	
	public int getQuantity() {
		return stockQuantity;
	}
	
	public void setQuantity(int quantity) {
		this.stockQuantity = quantity;
	}

	public Date getDate() {
		return buyingDate;
	}
	
	public void setDate(Date buyingDate) {
		this.buyingDate = buyingDate;
	}

	
	public String getHtmlDescription(){
		String stockDetails = "<b>Stock symbol:</b> " + getSymbol() + 
				" <b>bid:</b> " + getBid() + " <b>ask: </b> "
				+ getAsk() + " <b>date:</b> " + this.sdf.format(this.buyingDate);
		return stockDetails;
	}
}
