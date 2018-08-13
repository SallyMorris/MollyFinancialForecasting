package com.molly.forecasting.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class UserHistoryDTO {

	private String userName;
	private int userId;
	private LocalDate dateLogin;
	private String timeLogin;
	private LocalDate dateLogout;
	private String timeLogout;

	public UserHistoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(LocalDate dateLogin) {
		this.dateLogin = dateLogin;
	}

	public String getTimeLogin() {
		return timeLogin;
	}

	public void setTimeLogin(String timeLogin) {
		this.timeLogin = timeLogin;
	}

	public LocalDate getDateLogout() {
		return dateLogout;
	}

	public void setDateLogout(LocalDate dateLogout) {
		this.dateLogout = dateLogout;
	}

	public String getTimeLogout() {
		return timeLogout;
	}

	public void setTimeLogout(String timeLogout) {
		this.timeLogout = timeLogout;
	}

}
