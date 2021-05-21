package edu.umb.cs681.hw18;

public class StockEvent{
	
	public String ticker;
	public float quote;

	public StockEvent(String t, float q) {
		this.ticker = t;
		this.quote = q;
	}
	public float getQuote()
	{
		return quote;
	}
	
	public String getTicker()
	{
		return ticker;
	}
}
