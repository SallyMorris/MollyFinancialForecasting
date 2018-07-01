package com.molly.forecasting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statement")
public class StatementController {
	
	@RequestMapping("/list")
    public String list() {
		return "statement/list";
    }
	
}
