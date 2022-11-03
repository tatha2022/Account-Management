package com.barclays.accountmanagement.entity;

//This class is for Debit and Credit transactions
public class TransactionRequestPayload {
	protected long accountNumber;
	protected double amount;
	protected String type; // debit or credit
	protected String subType;

	public TransactionRequestPayload() {

	}

	public TransactionRequestPayload(long accountNumber, double amount, String type) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
