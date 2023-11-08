package com.example.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.ProductItem;
import com.example.demo.pojo.ProductType;

@Repository
public class ProductTypeDaoImpl implements ProductTypeDao {
	
	SessionFactory factory = null;
	Session session = null;
	
	public void init() {
		
		factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(ProductType.class)
			.addAnnotatedClass(ProductItem.class)
			.buildSessionFactory();

		session = factory.getCurrentSession();
	}

	@Override
	public void saveProductType(ProductType productType) {
		init();
		
		try {
			session.beginTransaction();

			session.save(productType);

			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public Iterable getProductType() {
		
		init();
		
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("from ProductType");
			
			List<ProductType> productType = query.list();

			return productType;
			
		} finally {
			session.close();
			factory.close();
		}
	}
	
	@Override
	public void saveUser(ProductType productType) {
		
		init();
	
		try {
			session.beginTransaction();

			session.save(productType);

			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void addProductType(ProductType productType) {
		saveUser(productType);
	}

	@Override
	public void deleteProductType(ProductType productType) {
		init();
		
		try {
			session.beginTransaction();
			
			session.delete(productType);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void updateProductType(ProductType productType) {
		init();
		
		try {
			session.beginTransaction();
			
			session.update(productType);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public ProductType findProductTypeByID(int productId) {
		init();
		
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("from ProductType u where u.id = :id");
			
			query.setParameter("id", productId);
			
			ProductType productType = (ProductType) query.uniqueResult();

			return productType;
			
		} finally {
			session.close();
			factory.close();
		}
	}
}
