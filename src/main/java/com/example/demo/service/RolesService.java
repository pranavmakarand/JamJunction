package com.example.demo.service;

import com.example.demo.pojo.Role;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RolesService {

	void addNewRoles(Role role);

	Iterable<Role> getAllRoles(int userId);

	void deleteRoles(Role role);

	void updateRoles(Role roles);

	Role findRoleByID(int ID);
}
