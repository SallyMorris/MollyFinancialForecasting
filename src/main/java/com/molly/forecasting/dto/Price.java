package com.molly.forecasting.dto;

public class Price {
	private String date;
	private float open;
	private float high;
	private float low;
	private float close;
	private String dayName;
	private String priceDirection;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public float getClose() {
		return close;
	}
	public void setClose(float close) {
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
