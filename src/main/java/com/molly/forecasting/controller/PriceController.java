package com.molly.forecasting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/price")
public class PriceController {
	
	@RequestMapping("/list")
    public String list() {
		return "price/list";
    }
	
}
