package com.myorg.javacourse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Stock {
	private String symbol;
	private float ask, bid;
	private Date buyingDate;
	private int recommendation;
	private int stockQuantity;
	private static final int BUY = 0;
	private static final int SELL = 1;
	private static final int REMOVE = 2;
	private static final int HOLD = 3;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	public Stock(String symbol, float ask, float bid, Date buyingDate){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.buyingDate = buyingDate;
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
	public void setBid(float bid) {
		this.bid = bid;
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
