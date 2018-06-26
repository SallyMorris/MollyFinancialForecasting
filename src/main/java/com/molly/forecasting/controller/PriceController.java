package com.molly.forecasting.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.molly.forecasting.service.PriceService;

@Controller
@RequestMapping("/price")
public class PriceController {
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	PriceService priceService;
	
	@RequestMapping("/list")
    public String list() {
		return "price/list";
    }
	
}
