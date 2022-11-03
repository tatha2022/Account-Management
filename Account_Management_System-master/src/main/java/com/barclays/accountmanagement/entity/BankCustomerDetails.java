package com.barclays.accountmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

//Table to add Customer Details
@Entity
@Table(name = "BankCustomerDetails")
public class BankCustomerDetails {
	@Id
	@NotNull
	@Min(value =6, message="Customer ID is mandatory")
	@Max(value = 6)
	public long customerId;
	
	@NotEmpty
	@Size(min = 11, max = 11, message="PAN Number is mandatory and should be of 11 characters")
	public String panNumber;
	
	@Min(value =12)
	@Max(value = 12, message="Aadhar Number is mandatory and should be of 12 digits")
	public String aadharNumber;

	public String name;
	public String address;
	
	@Email
	public String emailAddress;
//	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	public LocalDate dob;

	public BankCustomerDetails() {
		this.customerId = generateCustomerId();

	}

	public BankCustomerDetails(String panNumber, String aadharNumber, String name, String address, String emailAddress,
			LocalDate dob, double currentBalance) {
		super();
		this.customerId = generateCustomerId();
		this.panNumber = panNumber;
		this.aadharNumber = aadharNumber;
		this.name = name;
		this.address = address;
		this.emailAddress = emailAddress;
		this.dob = dob;

	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public LocalDate getDOB() {
		return dob;
	}

	public void setDOB(LocalDate dob) {
		this.dob = dob;
	}

	public long generateCustomerId() {
		long customerId = (long) Math.floor(Math.random() * 9_000_00L) + 1_000_00L;
		return customerId;
	}

}
