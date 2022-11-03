package com.barclays.accountmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.accountmanagement.dao.TransactionRepo;
import com.barclays.accountmanagement.entity.Transaction;

//Service class for Transaction
@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	
	public Optional<Transaction> getTransaction(String transactionId) {
		
		return transactionRepo.findById((Integer.parseInt(transactionId)));
		
	}
	
	public Transaction createTransaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}
	public List<Transaction> findByAccountNumber(long accountNumber) {
		return transactionRepo.findByAccountNumber(accountNumber);
	}
	public boolean getByPerDayLimit(long accountNumber,double amountToWithdraw) {
		
		if((transactionRepo.getByPerDayLimit(accountNumber)+amountToWithdraw)<=10000) {
			System.out.println("Your daily spent amount is "+transactionRepo.getByPerDayLimit(accountNumber)+amountToWithdraw);
		return true;
		}
		/*else if(transactionRepo.getByPerDayLimit(accountNumber)==0.0) {
			return true;
		}*/
		else {
			return false;
		}
	}
}