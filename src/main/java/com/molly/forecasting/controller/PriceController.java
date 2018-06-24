package com.molly.forecasting.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.molly.forecasting.dto.PriceDTO;
import com.molly.forecasting.service.PriceService;

@RestController
public class PriceController {
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	PriceService priceService;
	
	@RequestMapping("/price")
    public PriceDTO list() {
        return priceService.getPriceByticker("AAPL","2010-01-01","2018-06-22");
    }
	
}
