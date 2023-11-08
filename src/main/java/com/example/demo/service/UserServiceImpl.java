package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired // This means to get the bean called userRepository
	private UserDao userRepository;

	@Override
	public void addNewUser(User user) {
		userRepository.addUser(user);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.getUsers();
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.deleteUser(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

	@Override
	public User findUserByID(int ID) {
		return userRepository.findUserByID(ID);
	}
	
	@Override
	public void saveRole(User user) {
		userRepository.saveRole(user);
	}

//  @Autowired
//  protected SessionFactory sessionFactory;

//  public void addNewUser (User user) {
//	  
//    userRepository.save(user);
//  }
//
//  public Iterable<User> getAllUsers() {
//    return userRepository.findAll();
//  }

//  public Boolean checkIfUserExists(User user) {
//	  
////	  Iterable<User> users = getAllUsers();
////	  
////	  for (User us : users) {
////		  if (us.getName().equals(user.getName())) {
////			  return true;
////		  } 
////	  }
//
//	  return false;
//  }
//
//	@Override
//	public User findUserByEmail(String email) {
//		return userRepository.findUserFromEmail(email);
//	}
//
//	@Override
//	public void addNewUser(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Iterable<User> getAllUsers() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
