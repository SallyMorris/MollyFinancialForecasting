package com.molly.forecasting.dto;

public class StandardizedFinancials {
	private String tag;
	private Object value;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Double getValue() {
		return (Double) value;
	}

	public void setValue(Object value) {
		if (value.getClass().getName() != "java.lang.String") 
			this.value = value;
	}

}
