package com.molly.forecasting.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class PriceService {

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * Get Data from API URI: https://api.intrinio.com/prices
	*/
	public List<Price> getPriceByticker(String ticker) {
		RestTemplate restTemplate = new RestTemplate();
		PriceDTO priceDTO = new PriceDTO();
		String pricesUrl = "https://api.intrinio.com/prices?identifier=" + ticker;
		// don't forget to remove it
		AuthenticateIntrinioService.setAuth("b7a297bb22c7f89c3429cff7203fabb6", "e8d98b6f24154312aa369a9ed43888f7");
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
