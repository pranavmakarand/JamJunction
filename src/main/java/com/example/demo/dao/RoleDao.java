package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Role;

@Repository
public interface RoleDao {
	
	public void addUserRole(Role role);
	
	public void deleteUserRole(Role role);
	
	public void updateUserRole(Role role);
	
	public Role findUserRoleByID(int roleId);

	Iterable getUserRoles(int userId);
}
