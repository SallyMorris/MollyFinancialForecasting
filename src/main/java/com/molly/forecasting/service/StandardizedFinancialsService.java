package com.molly.forecasting.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static Logger logger = LogManager.getLogger();

	/*
	 * Get Data from API URI: https://api.intrinio.com/financials/standardized
	 */
	public List<StatementDTO> getStatementByticker(String ticker) {
		RestTemplate restTemplate = new RestTemplate();
		StandardizedFinancialsDTO financialsDTO = new StandardizedFinancialsDTO();
		// don't forget to remove it
		AuthenticateIntrinioService.setAuth("2b133ee185d14bf025458bde2348eb31", "15878798867d67e11397dd02587bb057");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", AuthenticateIntrinioService.auth);
		List<StatementDTO> statements = new ArrayList<>();
		String years[] = { "2013", "2014", "2015", "2016", "2017" };
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
					case "operatingrevenue":
						statementDTO.setOperatingRevenue(financialsDTO.getData().get(i).getValue());
						break;
					case "totalrevenue":
						statementDTO.setTotalRevenue(financialsDTO.getData().get(i).getValue());
						break;
					case "operatingcostofrevenue":
						statementDTO.setOperatingCostOfRevenue(financialsDTO.getData().get(i).getValue());
						break;
					case "totalcostofrevenue":
						statementDTO.setTotalCostOfRevenue(financialsDTO.getData().get(i).getValue());
						break;
					case "totalgrossprofit":
						statementDTO.setTotalGrossProfit(financialsDTO.getData().get(i).getValue());
						break;
					case "totaloperatingexpenses":
						statementDTO.setTotalOperatingExpenses(financialsDTO.getData().get(i).getValue());
						break;
					case "totaloperatingincome":
						statementDTO.setTotalOperatingIncome(financialsDTO.getData().get(i).getValue());
						break;
					case "netincome":
						statementDTO.setNetIncome(financialsDTO.getData().get(i).getValue());
						break;
					case "cashandequivalents":
						statementDTO.setCashAndEquivalents(financialsDTO.getData().get(i).getValue());
						break;
					case "notereceivable":
						statementDTO.setNoteReceivable(financialsDTO.getData().get(i).getValue());
						break;
					case "netinventory":
						statementDTO.setNetInventory(financialsDTO.getData().get(i).getValue());
						break;
					case "netppe":
						statementDTO.setNetPPE(financialsDTO.getData().get(i).getValue());
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
