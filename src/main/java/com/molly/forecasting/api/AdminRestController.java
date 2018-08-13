package com.molly.forecasting.api;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.molly.forecasting.dto.UserHistoryDTO;
import com.molly.forecasting.entity.UserHistory;
import com.molly.forecasting.service.UserHistoryService;

@RestController
@RequestMapping("/admin/api")
public class AdminRestController {
	
	@Autowired
	private UserHistoryService userHistoryService;
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	@ResponseBody
	public List<UserHistoryDTO> getPriceDataByticker(String ticker) {
		List<UserHistoryDTO> userHistory = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
		userHistoryService.getAllUsersHistory().forEach(item->{
			UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
			userHistoryDTO.setUserId(item.getUser().getId());
			userHistoryDTO.setUserName(item.getUser().getName()+" "+item.getUser().getLastName());
			userHistoryDTO.setDateLogin(item.getDateLogin().toLocalDateTime().toLocalDate());
			userHistoryDTO.setTimeLogin(item.getDateLogin().toLocalDateTime().toLocalTime().format(formatter));
			userHistoryDTO.setDateLogout(item.getDateLogout().toLocalDateTime().toLocalDate());
			userHistoryDTO.setTimeLogout(item.getDateLogout().toLocalDateTime().toLocalTime().format(formatter));
			userHistory.add(userHistoryDTO);
		});
		return userHistory;
	}
	
}
