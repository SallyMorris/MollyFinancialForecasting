package com.molly.forecasting.service;

import com.molly.forecasting.entity.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
