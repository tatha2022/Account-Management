package com.barclays.accountmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.accountmanagement.entity.AccountInfo;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {

}
