package com.molly.forecasting.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.molly.forecasting.dto.StatementDTO;
import com.molly.forecasting.service.StandardizedFinancialsService;

@RestController
@RequestMapping("/api")
public class StatementRestController {

	@Autowired
	StandardizedFinancialsService financialsService;

	@RequestMapping(value = "/statement", method = RequestMethod.GET)
	@ResponseBody
	public List<StatementDTO> getPriceDataByticker(String ticker,String years) {
		return financialsService.getStatementByticker(ticker,years);
	}
	
	
	
	
	
}
