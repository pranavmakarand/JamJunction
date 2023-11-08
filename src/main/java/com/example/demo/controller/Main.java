package com.example.demo.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.demo.pojo.ProductItem;
import com.example.demo.pojo.ProductType;

public class Main {
	
	public static void main(String args[]) {
		
		SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(ProductType.class)
			.addAnnotatedClass(ProductItem.class)
			.buildSessionFactory();
			
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			ProductType tempInstructor = session.get(ProductType.class, 8);		
			
			// create some courses
			ProductItem tempCourse1 = new ProductItem();
			
			tempCourse1.setId(18);
			tempCourse1.setName("shirts");
			tempCourse1.setPrice(100);
			tempCourse1.setProductType(tempInstructor);
			
			// add courses to instructor
			tempInstructor.addProductItem(tempCourse1);

			// save the courses
			session.save(tempCourse1);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}
}
