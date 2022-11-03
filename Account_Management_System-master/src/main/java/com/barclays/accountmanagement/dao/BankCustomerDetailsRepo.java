package com.barclays.accountmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barclays.accountmanagement.entity.BankCustomerDetails;

public interface BankCustomerDetailsRepo extends JpaRepository<BankCustomerDetails, Integer>{
	
	@Query(value = "select * from bank_customer_details where pan_number =?1",nativeQuery = true)
	BankCustomerDetails findByPannumber(String pannumber);

	BankCustomerDetails findByCustomerId(Long customerId);

}
