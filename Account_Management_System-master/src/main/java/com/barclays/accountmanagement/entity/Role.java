 	package com.barclays.accountmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//Table to add Role Details
@Entity
@Table(name = "Role")
public class Role {
	@Id
	@NotNull
	public int roleId;
	public String roleName;

	public Role(@NotNull int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
