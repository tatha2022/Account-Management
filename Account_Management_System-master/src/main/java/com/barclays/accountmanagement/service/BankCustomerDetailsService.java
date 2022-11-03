package com.barclays.accountmanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.accountmanagement.dao.BankAccountRepo;
import com.barclays.accountmanagement.dao.BankCustomerDetailsRepo;
import com.barclays.accountmanagement.entity.BankCustomerDetails;

//Service Class for BankCustomerDetails
@Service
@Transactional
public class BankCustomerDetailsService {

	@Autowired
	private BankCustomerDetailsRepo bankCustomerDetailsRepo;
	@Autowired
	private BankAccountRepo bankAccountRepo;
	//Create
	public BankCustomerDetails createNewCusomer(BankCustomerDetails bankCustomerDetails) {
		
		
		bankCustomerDetailsRepo.save(bankCustomerDetails);
		
		return bankCustomerDetails;
	}
	
	//Read
	public BankCustomerDetails getCustomerDetails(Long customerId) {
		return  bankCustomerDetailsRepo.findByCustomerId(customerId);
			
	}
	
	//Update
	
	
	//Delete - 
	public void deleteCustomer(Integer customerId) {
		bankCustomerDetailsRepo.deleteById(customerId);
		
	}
	public BankCustomerDetails getDetailsByPan(String pannumber) {
		
		return bankCustomerDetailsRepo.findByPannumber(pannumber);
		
	}
}
