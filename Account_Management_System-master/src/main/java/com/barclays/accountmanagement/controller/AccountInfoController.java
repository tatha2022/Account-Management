package com.barclays.accountmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.accountmanagement.entity.AccountInfo;
import com.barclays.accountmanagement.service.AccountInfoService;

@RestController
public class AccountInfoController {

	@Autowired
	private AccountInfoService accountInfoService;

//	@GetMapping("/customers/{acc}")
//	private List<AccountInfo> getAllAccounts() {
//		return accountInfoService.getAllAccounts();
//	}

	@PostMapping("/customers/addTransaction")
	private AccountInfo addTransaction(@RequestBody AccountInfo accountInfo) {
		return accountInfoService.addTransaction(accountInfo);

	}

	@GetMapping("/customers/{accno}")
	private List<AccountInfo> findByAccountNo(@PathVariable long accno) {
		return accountInfoService.findByAccountNo(accno);
	}

}
