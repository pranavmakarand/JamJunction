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
public class ProductItemDaoImpl implements ProductItemDao {

	SessionFactory factory = null;
	Session session = null;

	public void init() {

		factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(ProductType.class)
			.addAnnotatedClass(ProductItem.class).buildSessionFactory();

		session = factory.getCurrentSession();
	}

	@Override
	public void saveProductItem(ProductItem productItem) {

	}

	@Override
	public Iterable getProductItem(int productType) {

		init();
		
		try {
			session.beginTransaction();
		
			Query query = session.createQuery("from ProductItem u where u.productType.id = :productTypeId");
			
			query.setParameter("productTypeId", productType);
			
			List<ProductItem> productItem = query.list();

			return productItem;
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void addProductItem(ProductItem productItem) {
		
		init();

		try {
			session.beginTransaction();

			session.save(productItem);

			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void deleteProductItem(ProductItem productItem) {
		init();
		
		try {
			session.beginTransaction();
			
			session.delete(productItem);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void updateProductItem(ProductItem productItem) {
		init();
		
		try {
			session.beginTransaction();
			
			session.update(productItem);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public ProductItem findProductItemByID(int productId) {
		init();
		
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("from ProductItem u where u.id = :id");
			
			query.setParameter("id", productId);
			
			ProductItem productItem = (ProductItem) query.uniqueResult();

			return productItem;
			
		} finally {
			session.close();
			factory.close();
		}
	}
}
