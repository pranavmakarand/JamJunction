package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.pojo.ProductType;

@Repository
public interface ProductTypeDao {
	
	public void saveProductType(ProductType productType);
	
	public Iterable getProductType();
	
	public void addProductType(ProductType productType);
	
	public void deleteProductType(ProductType productType);
	
	public void updateProductType(ProductType productType);
	
	public ProductType findProductTypeByID(int productId);

	void saveUser(ProductType productType);
}
