package com.molly.forecasting.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.molly.forecasting.dto.Price;
import com.molly.forecasting.dto.PriceDTO;
import com.molly.forecasting.naivebayes.DataSet;

import java.util.*;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PriceService {

	private static Logger logger = LogManager.getLogger();

	/*
	 * Get Data from API URI: https://api.intrinio.com/prices
	*/
	public List<Price> getPriceByticker(String ticker) {
		RestTemplate restTemplate = new RestTemplate();
		PriceDTO priceDTO = new PriceDTO();
		String pricesUrl = "https://api.intrinio.com/prices?identifier=" + ticker;
		// don't forget to remove it
		AuthenticateIntrinioService.setAuth("2b133ee185d14bf025458bde2348eb31", "15878798867d67e11397dd02587bb057");
		logger.info("GET: " + pricesUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization",AuthenticateIntrinioService.auth);
		HttpEntity<String> requestEntity = new HttpEntity<>("Headers", httpHeaders);
		ResponseEntity<PriceDTO> responseEntity = restTemplate.exchange(pricesUrl, HttpMethod.GET, requestEntity,
				PriceDTO.class);
		priceDTO.setData(responseEntity.getBody().getData());
		Iterator<Price> iterator = priceDTO.getData().iterator();
		while (iterator.hasNext()) {
			Price price = iterator.next();
			try {
				price.setDayName(dayName(price.getDate()));
			} catch (Exception e) {
				logger.warn("Can't Parse Date: {}", e.getMessage());
				break;
			}
			price.setPriceDirection(price.getClose() > price.getOpen() ? "UP" : "DOWN");
		}
		return priceDTO.getData();
	}

	public static String dayName(String inputDate) throws Exception {
			return new SimpleDateFormat("EEEE").format(new SimpleDateFormat("yyyy-M-d").parse(inputDate));
		
	}
	
	public List<String> forecastPrice(String ticker,String dayName) {
		List<Price> priceList = new PriceService().getPriceByticker(ticker);
		
		String[][] stockList = new String[priceList.size()+1][];
		stockList[0]= new String[]{ "DayOfWeek", "Price Direction" };
		for (int i = 0; i < stockList.length-1; i++) {			
			stockList[i+1]= new String[]{ priceList.get(i).getDayName(), priceList.get(i).getPriceDirection() };
		}
		DataSet dataSet = new DataSet(stockList);
		System.out.println("[DATASET]\n" + dataSet);
		String dayOfWeek = dayName;
		HashMap<String, String> instMap = new HashMap<String, String>();
		instMap.put(dataSet.getData()[0][0], dayOfWeek);

		HashMap<String, Double> condProbs = dataSet.calcCondProbs(instMap);
		double allProbs = 0;
		Iterator<Double> probsIterator = condProbs.values().iterator();
		while (probsIterator.hasNext()) {
			allProbs += probsIterator.next();
		}
		Iterator<String> keyIterator = condProbs.keySet().iterator();
		List<String> resultList = new ArrayList<>();
		while (keyIterator.hasNext()) {
			String next = keyIterator.next();
			resultList.add("P(" + next + "|" + DataSet.getInstanceStr(dataSet, instMap) + ") = "
					+ String.format("%.5f", condProbs.get(next)) + "/" + String.format("%.5f", allProbs) + " = "
					+ String.format("%.5f", condProbs.get(next) / allProbs));
		}
		
		return resultList;
	}
}
