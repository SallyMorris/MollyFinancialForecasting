package com.molly.forecasting.dto;

import java.util.List;

public class PriceDTO {
	private Integer result_count;
	private List<Price> data;
	
	public Integer getResult_count() {
		return result_count;
	}
	public void setResult_count(Integer result_count) {
		this.result_count = result_count;
	}
	public List<Price> getData() {
		return data;
	}
	public void setData(List<Price> data) {
		this.data = data;
	}
	
	
}
