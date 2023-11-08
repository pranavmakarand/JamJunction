package com.example.demo.service;

import com.example.demo.pojo.ProductItem;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductItemService {

	void addNewProductItem(ProductItem user);

	Iterable<ProductItem> getAllProductItem(int productTypeId);

	void deleteProductItem(ProductItem productType);

	void updateProductItem(ProductItem productType);

	ProductItem findProductItemByID(int ID);
}
