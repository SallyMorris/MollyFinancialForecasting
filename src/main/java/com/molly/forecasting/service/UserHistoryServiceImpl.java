package com.molly.forecasting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molly.forecasting.entity.UserHistory;
import com.molly.forecasting.repository.UserHistoryRepository;

@Service("UserHistoryService")
public class UserHistoryServiceImpl implements UserHistoryService {

	@Autowired
	UserHistoryRepository userHistoryRepository;

	@Override
	public List<UserHistory> getAllUsersHistory() {
		return userHistoryRepository.findAll();

	}

}
