package com.molly.forecasting.dto;

public class Price {
	private String date;
	private Float open;
	private Float high;
	private Float low;
	private Float close;
	private String dayName;
	private String priceDirection;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Float getOpen() {
		return open;
	}
	public void setOpen(Float open) {
		this.open = open;
	}
	public Float getHigh() {
		return high;
	}
	public void setHigh(Float high) {
		this.high = high;
	}
	public Float getLow() {
		return low;
	}
	public void setLow(Float low) {
		this.low = low;
	}
	public Float getClose() {
		return close;
	}
	public void setClose(Float close) {
		this.close = close;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getPriceDirection() {
		return priceDirection;
	}
	public void setPriceDirection(String priceDirection) {
		this.priceDirection = priceDirection;
	}
	
	
		
}
