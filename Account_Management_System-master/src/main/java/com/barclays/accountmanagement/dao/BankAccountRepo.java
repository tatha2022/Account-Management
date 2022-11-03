package com.barclays.accountmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.barclays.accountmanagement.entity.BankAccount;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long>{
	@Modifying
	@Query(value ="update bank_account set current_balance=?1 where account_number=?2", nativeQuery = true)
	void updateAccount(double currentBalance,long accountNumber);
	
}