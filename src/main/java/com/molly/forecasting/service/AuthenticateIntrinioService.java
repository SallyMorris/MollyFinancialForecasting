package com.molly.forecasting.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class AuthenticateIntrinioService {

	static String auth;

	public static void setAuth(String userName, String password) {
		String credentials = userName + ":" + password;
		byte[] encodedBytes = Base64.getEncoder().encode(credentials.getBytes());
		AuthenticateIntrinioService.auth = "Basic " + new String(encodedBytes);
	}
}
