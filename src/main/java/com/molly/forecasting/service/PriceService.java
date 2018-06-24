package com.molly.forecasting.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.molly.forecasting.dto.PriceDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PriceService {
	
	
    private static Logger logger = LogManager.getLogger();
    
    private final static String auth = "Basic MmIxMzNlZTE4NWQxNGJmMDI1NDU4YmRlMjM0OGViMzE6MTU4Nzg3OTg4NjdkNjdlMTEzOTdkZDAyNTg3YmIwNTc="; 
    
    /*
     * Get Data from API URI: https://api.intrinio.com
     */
    public PriceDTO getPriceByticker(String ticker,String startDate,String endDate) {
    	RestTemplate restTemplate = new RestTemplate();
    	PriceDTO priceDTO = new PriceDTO();
    	String pricesUrl = "https://api.intrinio.com/prices?identifier="+ticker+"&start_date="+startDate+"&end_date="+endDate+"&page_size=3000";
		logger.info("GET: "+pricesUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization",auth);
		HttpEntity<String> requestEntity = new HttpEntity<>("Headers", httpHeaders);
		logger.info("Respons: "+ restTemplate.exchange(pricesUrl, HttpMethod.GET, requestEntity, String.class).getBody());
		ResponseEntity<PriceDTO> responseEntity = restTemplate.exchange(pricesUrl, HttpMethod.GET, requestEntity, PriceDTO.class);
		logger.info("resultCount: "+ responseEntity.getBody().getResult_count());
		logger.info("List: "+ responseEntity.getBody().getData());
		priceDTO.setResult_count(responseEntity.getBody().getResult_count());
		priceDTO.setData(responseEntity.getBody().getData());
		return priceDTO;
    }

}
