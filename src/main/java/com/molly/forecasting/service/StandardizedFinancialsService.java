package com.molly.forecasting.service;

import java.util.ArrayList;
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

import com.molly.forecasting.dto.StandardizedFinancialsDTO;
import com.molly.forecasting.dto.StatementDTO;

@Service
public class StandardizedFinancialsService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Get Data from API URI: https://api.intrinio.com/financials/standardized
	 */
	public List<StatementDTO> getStatementByticker(String ticker, String yearsConcatenated) {
		RestTemplate restTemplate = new RestTemplate();
		StandardizedFinancialsDTO financialsDTO = new StandardizedFinancialsDTO();
		// don't forget to remove it
		AuthenticateIntrinioService.setAuth("74c4a79e01d5ce91d12f0bf1f18e50fd", "e5f0961185335d7bf79acd8efd846e18");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", AuthenticateIntrinioService.auth);
		List<StatementDTO> statements = new ArrayList<>();
		String years[] = yearsConcatenated.split(",");
		String statementTypes[] = { "income_statement", "balance_sheet", "calculations" };
		for (int k = 0; k < years.length; k++) {
			StatementDTO statementDTO = new StatementDTO();
			for (int j = 0; j < statementTypes.length; j++) {
				String standardizedFinancialsUrl = "https://api.intrinio.com/financials/standardized?identifier="
						+ ticker + "&statement=" + statementTypes[j] + "&fiscal_year=" + years[k] + "&fiscal_period=FY";
				HttpEntity<String> requestEntity = new HttpEntity<>("Headers", httpHeaders);
				logger.info("GET: " + standardizedFinancialsUrl);
				ResponseEntity<StandardizedFinancialsDTO> responseEntity = restTemplate.exchange(
						standardizedFinancialsUrl, HttpMethod.GET, requestEntity, StandardizedFinancialsDTO.class);
				financialsDTO.setData(responseEntity.getBody().getData());
				statementDTO.setYear(years[k]);
				for (int i = 0; i < financialsDTO.getData().size() - 1; i++) {
					switch (financialsDTO.getData().get(i).getTag()) {
					case "investmentsecuritiesinterestincome":
						statementDTO.setInvestmentSecuritiesInterestIncome(financialsDTO.getData().get(i).getValue());
						break;
					case "totalrevenue":
						statementDTO.setTotalRevenue(financialsDTO.getData().get(i).getValue());
						break;
					case "totalinterestincome":
						statementDTO.setTotalInterestIncome(financialsDTO.getData().get(i).getValue());
						break;
					case "totalinterestexpense":
						statementDTO.setTotalInterestExpense(financialsDTO.getData().get(i).getValue());
						break;
					case "salariesandemployeebenefitsexpense":
						statementDTO.setSalariesAndEmployeeBenefitsExpense(financialsDTO.getData().get(i).getValue());
						break;
					case "netincome":
						statementDTO.setNetIncome(financialsDTO.getData().get(i).getValue());
						break;
					case "cashandequivalents":
						statementDTO.setCashAndEquivalents(financialsDTO.getData().get(i).getValue());
						break;
					case "totalliabilities":
						statementDTO.setTotalLiabilities(financialsDTO.getData().get(i).getValue());
						break;
					case "totalcommonequity":
						statementDTO.setTotalCommonequity(financialsDTO.getData().get(i).getValue());
						break;
					case "totalequity":
						statementDTO.setTotalEquity(financialsDTO.getData().get(i).getValue());
						break;
					case "totalassets":
						statementDTO.setTotalAssets(financialsDTO.getData().get(i).getValue());
						break;
					case "leverageratio":
						statementDTO.setLeverageRatio(financialsDTO.getData().get(i).getValue());
						break;
					case "noncontrollinginterestsharingratio":
						statementDTO.setNonControllingInterestSharingRatio(financialsDTO.getData().get(i).getValue());
						break;
					case "divpayoutratio":
						statementDTO.setDivPayoutRatio(financialsDTO.getData().get(i).getValue());
						break;
					case "augmentedpayoutratio":
						statementDTO.setAugmentedPayoutRatio(financialsDTO.getData().get(i).getValue());
						break;

					default:
						break;
					}
				}
			}
			statements.add(statementDTO);
		}
		return statements;
	}

}
