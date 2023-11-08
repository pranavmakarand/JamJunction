package com.example.demo.service;

import com.example.demo.pojo.ProductType;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductTypeService {

	void addNewProductType(ProductType user);

	Iterable<ProductType> getAllProductType();

	void deleteProductType(ProductType productType);

	void updateProductType(ProductType productType);

	ProductType findProductTypeByID(int ID);
}
