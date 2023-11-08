package com.example.demo.dao;

import com.example.demo.pojo.ProductItem;

public interface ProductItemDao {
	public void saveProductItem(ProductItem productItem);
	
	public void addProductItem(ProductItem productItem);
	
	public void deleteProductItem(ProductItem productItem);
	
	public void updateProductItem(ProductItem productItem);
	
	public ProductItem findProductItemByID(int productId);

	Iterable getProductItem(int productType);
}
