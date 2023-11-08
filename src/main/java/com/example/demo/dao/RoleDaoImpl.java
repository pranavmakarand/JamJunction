package com.example.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;

@Repository
public class RoleDaoImpl implements RoleDao {

	SessionFactory factory = null;
	Session session = null;

	public void init() {

		factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(User.class)
			.addAnnotatedClass(Role.class).buildSessionFactory();

		session = factory.getCurrentSession();
	}

	@Override
	public void addUserRole(Role role) {
		
		init();

		try {
			session.beginTransaction();

			session.save(role);

			session.getTransaction().commit();
			
		} finally {
			
			session.close();
			factory.close();
			
		}
	}

	@Override
	public void deleteUserRole(Role role) {
		
		init();
		
		try {
			session.beginTransaction();
			
			session.delete(role);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void updateUserRole(Role role) {
		init();
		
		try {
			session.beginTransaction();
			
			session.update(role);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public Role findUserRoleByID(int roleId) {
		init();
		
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("from Role u where u.id = :id");
			
			query.setParameter("id", roleId);
			
			Role role = (Role) query.uniqueResult();

			return role;
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public Iterable getUserRoles(int userId) {
		
		init();
		
		try {
			session.beginTransaction();
		
			Query query = session.createQuery("from Role u where u.user.id = :userId");
			
			query.setParameter("userId", userId);
			
			List<Role> roles = query.list();

			return roles;
			
		} finally {
			session.close();
			factory.close();
		}
	}
}
