package com.molly.forecasting.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.molly.forecasting.dto.Price;
import com.molly.forecasting.service.PriceService;

@RestController
@RequestMapping("/api")
public class PriceRestController {

	@Autowired
	PriceService priceService;

	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public List<Price> getPriceDataByticker(String ticker) {
		return priceService.getPriceByticker(ticker);
	}

	@RequestMapping(value = "/priceForecasting", method = RequestMethod.GET)
	@ResponseBody
	public List<String> forecaste(String ticker, String dayName) {
		return priceService.forecastPrice(ticker, dayName);
	}

}
