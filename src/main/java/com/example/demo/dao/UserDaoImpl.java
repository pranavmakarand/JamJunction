package com.example.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.service.RolesService;

@Repository
public class UserDaoImpl implements UserDao {

	SessionFactory factory = null;
	Session session = null;
	
	@Autowired
	RolesService roleService;

	public void init() {

		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).buildSessionFactory();

		session = factory.getCurrentSession();
	}

	@Override
	public void saveUser(User user) {

		init();

		try {
			session.beginTransaction();
			
//			session.save(user);
			
			
//			session.close();
			
//			session.beginTransaction();
			
//			User tempInstructor = session.get(User.class, user.getId());
			
//			Role role = new Role("user");
//			
//			role.setRoleId(5);
//			
//			role.setUser(user);
//
//			// add courses to instructor
//			user.addUserRole(role);
			

			// save the courses
			session.save(user);
			
			// commit transaction
			session.getTransaction().commit();
		
			
		} finally {
			session.close();
			factory.close();
		}
	}
	
	@Override
	public void saveRole(User user) {

		init();

		try {
			session.beginTransaction();
			
			User userr = session.get(User.class, user.getId());		
			
			// create some courses
			Role role = new Role();
	
			role.setRoleName("user");
			role.setUser(userr);
			
			// add courses to instructor
			userr.addUserRole(role);

			// save the courses
			session.save(role);
			
			// commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public User findUserByEmail(String email) {

		init();

		try {
			
			session.beginTransaction();

			Query query = session.createQuery("from User u where u.email = :email");

			query.setParameter("email", email);

			User user = (User) query.uniqueResult();

			return user;

		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void addUser(User user) {
		saveUser(user);
	}

	@Override
	public Iterable getUsers() {

		init();

		try {
			session.beginTransaction();

			Query query = session.createQuery("from User");

			List<User> user = query.list();

			return user;

		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void deleteUser(User user) {

		init();

		try {
			session.beginTransaction();

			session.delete(user);

			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void updateUser(User user) {
		init();
		try {

			session.beginTransaction();

			session.update(user);

			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public User findUserByID(int ID) {

		init();

		try {
			session.beginTransaction();

			Query query = session.createQuery("from User u where u.id = :id");

			query.setParameter("id", ID);

			User user = (User) query.uniqueResult();

			return user;

		} finally {
			session.close();
			factory.close();
		}
	}
}
