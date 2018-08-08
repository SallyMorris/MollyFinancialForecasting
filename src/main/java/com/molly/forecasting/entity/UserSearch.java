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
@Table(name = "user_search")
public class UserSearch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_search_id")
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "date_search")
	private Timestamp dateSearch;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "first_company_id")
	private Company firstCompany;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "second_company_id")
	private Company secondCompany;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "third_company_id")
	private Company thirdCompany;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "fourth_company_id")
	private Company fourthCompany;

	@Column(name = "result")
	private String result;

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

	public Timestamp getDateSearch() {
		return dateSearch;
	}

	public void setDateSearch(Timestamp dateSearch) {
		this.dateSearch = dateSearch;
	}

	public Company getFirstCompany() {
		return firstCompany;
	}

	public void setFirstCompany(Company firstCompany) {
		this.firstCompany = firstCompany;
	}

	public Company getSecondCompany() {
		return secondCompany;
	}

	public void setSecondCompany(Company secondCompany) {
		this.secondCompany = secondCompany;
	}

	public Company getThirdCompany() {
		return thirdCompany;
	}

	public void setThirdCompany(Company thirdCompany) {
		this.thirdCompany = thirdCompany;
	}

	public Company getFourthCompany() {
		return fourthCompany;
	}

	public void setFourthCompany(Company fourthCompany) {
		this.fourthCompany = fourthCompany;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "UserSearch [id=" + id + ", user=" + user + ", dateSearch=" + dateSearch + ", firstCompany="
				+ firstCompany + ", secondCompany=" + secondCompany + ", thirdCompany=" + thirdCompany
				+ ", fourthCompany=" + fourthCompany + ", result=" + result + "]";
	}
}
