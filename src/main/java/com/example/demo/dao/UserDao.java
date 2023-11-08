package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.pojo.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserDao {
	
	public void saveUser(User user);
	
	public Iterable getUsers();
	
	public void addUser(User user);
	
	public void deleteUser(User user);

	public User findUserByEmail(String parameter);
	
	public User findUserByID(int parameter);

	public void updateUser(User user);

	public void saveRole(User user);
}
