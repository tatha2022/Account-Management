package com.barclays.accountmanagement.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@Min(value=10, message="Transaction ID will be of 10 alpanumeric value")
	public String transactionId;
	public long transactionRefNo;
	public LocalDate transactionDate;
	public LocalTime transactionTIme;
	public String transactionType;
	public String transactionSubType;
	public double transactionAmount;
	public long accountNumber;

	public Transaction() {
		this.transactionId = generateTransactionId();
		this.transactionRefNo = generateTransactionRefNo();
		this.transactionDate = LocalDate.now();
		this.transactionTIme = LocalTime.now();
	}

	public Transaction(String transactionType, String transactionSubType) {
		super();
		this.transactionId = generateTransactionId();
		this.transactionRefNo = generateTransactionRefNo();
		this.transactionDate = LocalDate.now();
		;
		this.transactionTIme = LocalTime.now();
		;
		this.transactionType = transactionType;
		this.transactionSubType = transactionSubType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getTransactionRefNo() {
		return transactionRefNo;
	}

	public void setTransactionRefNo(int transactionRefNo) {
		this.transactionRefNo = transactionRefNo;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public LocalTime getTransactionTIme() {
		return transactionTIme;
	}

	public void setTransactionTIme(LocalTime transactionTIme) {
		this.transactionTIme = transactionTIme;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionSubType() {
		return transactionSubType;
	}

	public void setTransactionSubType(String transactionSubType) {
		this.transactionSubType = transactionSubType;
	}

	public String generateTransactionId() {

		int leftLimit = 65; // letter 'A'
		int rightLimit = 91; // letter 'Z'
		int targetStringLength = 4;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
//	   System.out.println(String.format("%06d", number)); 
		transactionId = generatedString + String.format("%06d", number);
		System.out.println(generatedString + String.format("%06d", number));

		return transactionId;
	}

	public long generateTransactionRefNo() {

		Random rnd = new Random();
		long transactionRefNo = rnd.nextInt(999999);
		System.out.println(transactionRefNo);
		return transactionRefNo;
	}

}
