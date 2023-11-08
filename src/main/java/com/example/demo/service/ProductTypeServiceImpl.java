package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductTypeDao;
import com.example.demo.pojo.ProductType;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeDao productTypeRepository;

	@Override
	public void addNewProductType(ProductType user) {
		productTypeRepository.addProductType(user);
	}

	@Override
	public Iterable<ProductType> getAllProductType() {
		return productTypeRepository.getProductType();
	}

	@Override
	public void deleteProductType(ProductType productType) {
		productTypeRepository.deleteProductType(productType);
	}

	@Override
	public void updateProductType(ProductType productType) {
		productTypeRepository.updateProductType(productType);
	}

	@Override
	public ProductType findProductTypeByID(int ID) {
		return productTypeRepository.findProductTypeByID(ID);
	}
}
