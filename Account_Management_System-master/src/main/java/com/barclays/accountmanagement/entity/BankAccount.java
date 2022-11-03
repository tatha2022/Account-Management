package com.barclays.accountmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//Table for Bank Account of a Customer
@Entity
@Table(name = "BankAccount")
public class BankAccount {
	@Id
	@Min(value = 10, message="Account Number should be of 10 digits")
	@Max(value = 10)
	@NotNull
	public long accountNumber;

	@Min(value = 6, message="Customer ID should be of 6 digits")
	@Max(value = 6)
	public long customerId;

	private double currentBalance;

	public BankAccount() {
		this.accountNumber = generateAccountNumber();
		this.currentBalance = 0;
	}

	public BankAccount(long customerId, double currentBalance) {
		super();
		this.accountNumber = generateAccountNumber();
		this.customerId = customerId;
		this.currentBalance = currentBalance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public long generateAccountNumber() {
		long accountNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return accountNumber;
	}

}
