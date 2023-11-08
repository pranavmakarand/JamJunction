package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDao;
import com.example.demo.pojo.Role;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RoleDao roleRepository;

	@Override
	public void addNewRoles(Role role) {
		roleRepository.addUserRole(role);
	}

	@Override
	public Iterable<Role> getAllRoles(int userId) {
		return roleRepository.getUserRoles(userId);
	}

	@Override
	public void deleteRoles(Role role) {
		roleRepository.deleteUserRole(role);
	}

	@Override
	public void updateRoles(Role roles) {
		roleRepository.updateUserRole(roles);
	}

	@Override
	public Role findRoleByID(int ID) {
		return roleRepository.findUserRoleByID(ID);
	}
}
