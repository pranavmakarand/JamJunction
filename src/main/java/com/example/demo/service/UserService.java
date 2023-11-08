package com.example.demo.service;

import com.example.demo.pojo.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserService {
	
	public void addNewUser(User user);
	
	public void deleteUser(User user);
	
	public void updateUser(User user);

	public Iterable<User> getAllUsers();
	
	public User findUserByEmail(String email);
	
	public User findUserByID(int ID);

	void saveRole(User user);
}
