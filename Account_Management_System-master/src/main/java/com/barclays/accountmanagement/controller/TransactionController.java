package com.barclays.accountmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.accountmanagement.entity.BankAccount;
import com.barclays.accountmanagement.entity.Transaction;
import com.barclays.accountmanagement.entity.TransactionRequestPayload;
import com.barclays.accountmanagement.response.ResponseHandler;
import com.barclays.accountmanagement.service.BankAccountService;
import com.barclays.accountmanagement.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private BankAccountService bankAccountService;
	
	
	@GetMapping("/gettransaction/{transactionId}")
	public ResponseEntity<Object> getTransaction(@PathVariable String transactionId){
		
		try {
			
			Optional<Transaction> transaction= transactionService.getTransaction(transactionId);
			return ResponseHandler.generateResponse("Success", HttpStatus.OK,transaction);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,null);
		}
	}
	
	@PostMapping("/createtransaction")
	public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction){
		try {
			
			Transaction newtransaction = transactionService.createTransaction(transaction);
			return ResponseHandler.generateResponse("success", HttpStatus.CREATED, newtransaction);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	//Recent 5 transactions
	
	@GetMapping("/topfiveTransaction/{accountNumber}")
	public List<Transaction> getFiveTransaction(@PathVariable long accountNumber) {
		List<Transaction> transaction = transactionService.findByAccountNumber(accountNumber);
		return transaction;
	}
	
	
	@PostMapping("/transaction")
	public ResponseEntity<Object> transaction(@RequestBody TransactionRequestPayload transactionRequestPayload){
		Transaction transactionDetails = null;
		
		if(transactionRequestPayload.getType().equals("credit")) {
			
			if(credit(transactionRequestPayload.getAccountNumber(),transactionRequestPayload.getAmount())) {
				transactionDetails= updateTransactionDetails(transactionRequestPayload);
				return ResponseHandler.generateResponse("success", HttpStatus.OK,transactionDetails);
			}
	
		}else if(transactionRequestPayload.getType().equals("debit")) {
			if(debit(transactionRequestPayload.getAccountNumber(),transactionRequestPayload.getAmount())) {
				
				transactionDetails= updateTransactionDetails(transactionRequestPayload);
				return ResponseHandler.generateResponse("success", HttpStatus.OK,transactionDetails);
			}
			
			
		}
		
		return ResponseHandler.generateResponse("could not process your transaction", HttpStatus.BAD_REQUEST,null);
	}
	
	
	public boolean debit(long accountNumber,double amountToWithdraw) {
		try {
			BankAccount account = bankAccountService.findByAccountNumber(accountNumber);
			if(transactionService.getByPerDayLimit(accountNumber,amountToWithdraw) && amountToWithdraw<=10000){
			if(amountToWithdraw<=account.getCurrentBalance()) 
			{
				bankAccountService.updateByaccountNumber(account.getCurrentBalance()- amountToWithdraw, accountNumber);	
				return true;
			}
			}else {
				System.out.println("Per day limit exceed :(");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		return false;
		
			
	}
	
	public boolean credit(long accountNumber,double creditAmount) {
		try {
			BankAccount account = bankAccountService.findByAccountNumber(accountNumber);	
			bankAccountService.updateByaccountNumber(account.getCurrentBalance() + creditAmount, accountNumber);
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
			
		
	}
	
	public Transaction updateTransactionDetails(TransactionRequestPayload transactionRequestPayload){
		Transaction newTransaction= new Transaction();
		newTransaction.setTransactionType(transactionRequestPayload.getType());
		newTransaction.setTransactionSubType(transactionRequestPayload.getSubType());
		newTransaction.setAccountNumber(transactionRequestPayload.getAccountNumber());
		newTransaction.setTransactionAmount(transactionRequestPayload.getAmount());
		
		transactionService.createTransaction(newTransaction);
		return newTransaction;
	}
}
