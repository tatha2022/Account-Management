package com.barclays.accountmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.accountmanagement.entity.BankAccount;
import com.barclays.accountmanagement.response.ResponseHandler;
import com.barclays.accountmanagement.service.BankAccountService;

@RestController
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;
	
	@GetMapping("/getaccount/{accountNumber}")
	public ResponseEntity<Object> getBalance(@PathVariable Integer accountNumber) {
		try {
			BankAccount bankAccount = bankAccountService.findByAccountNumber(accountNumber);
			return ResponseHandler.generateResponse("Success", HttpStatus.OK, bankAccount);
			
		} catch (Exception e) {
			
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}
		
		
	}
	
	@PostMapping("/createaccount")
	public ResponseEntity<Object> upsertAccount(@RequestBody BankAccount bankAccount) {
		
		try {
			BankAccount newAccount = bankAccountService.createAccount(bankAccount);
			return ResponseHandler.generateResponse("Account Created Successfully", HttpStatus.CREATED, newAccount);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse("Failed to create new user", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		
	
	}
	
	
	
}

