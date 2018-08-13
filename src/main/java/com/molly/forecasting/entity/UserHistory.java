package com.molly.forecasting.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_history")
public class UserHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_history_id")
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "date_login")
	private Timestamp dateLogin;

	@Column(name = "date_logout")
	private Timestamp dateLogout;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(Timestamp dateLogin) {
		this.dateLogin = dateLogin;
	}

	public Timestamp getDateLogout() {
		return dateLogout;
	}

	public void setDateLogout(Timestamp dateLogout) {
		this.dateLogout = dateLogout;
	}

	@Override
	public String toString() {
		return "UserHistory [id=" + id + ", user=" + user + ", dateLogin=" + dateLogin + ", dateLogout=" + dateLogout
				+ "]";
	}
	

}
