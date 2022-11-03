package com.barclays.accountmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.accountmanagement.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}

