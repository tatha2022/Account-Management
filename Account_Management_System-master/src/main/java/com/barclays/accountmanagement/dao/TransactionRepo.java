package com.barclays.accountmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barclays.accountmanagement.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	@Query(value="select * from transaction where account_number=?1 order by transactiontime desc limit 0,5", nativeQuery=true)
	List<Transaction> findByAccountNumber(long accountNumber);
	
	@Query(value="select IFNULL((SUM(transaction_amount)), 0) as Total_per_day from transaction where account_number=?1 and transaction_date=curdate()", nativeQuery=true)
	double getByPerDayLimit(long accountNumber);
}
