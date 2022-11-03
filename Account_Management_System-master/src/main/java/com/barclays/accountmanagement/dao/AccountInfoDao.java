package com.barclays.accountmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.accountmanagement.entity.AccountInfo;

@Repository
public class AccountInfoDao {

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	public List<AccountInfo> getAllAccounts() {
		return accountInfoRepository.findAll();
	}

	public AccountInfo addTransaction(AccountInfo accountInfo) {
		return accountInfoRepository.save(accountInfo);

	}

	public List<AccountInfo> findByAccountNo(long accno) {
		List<AccountInfo> accountInfos = accountInfoRepository.findAll();
		List<AccountInfo> l = new ArrayList<>();
		for (AccountInfo acc : accountInfos) {
			if (acc.getAccno() == accno) {
				System.out.println(accno);
				l.add(acc);
			}
		}
		return l; 

	}

}
