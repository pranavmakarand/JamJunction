package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductItemDao;
import com.example.demo.pojo.ProductItem;

@Service
public class ProductItemServiceImpl implements ProductItemService {

	@Autowired
	private ProductItemDao productItemRepository;

	@Override
	public void addNewProductItem(ProductItem productItem) {
		productItemRepository.addProductItem(productItem);
	}

	@Override
	public Iterable<ProductItem> getAllProductItem(int productTypeId) {
		return productItemRepository.getProductItem(productTypeId);
	}

	@Override
	public void deleteProductItem(ProductItem productItem) {
		productItemRepository.deleteProductItem(productItem);
	}

	@Override
	public void updateProductItem(ProductItem productItem) {
		productItemRepository.updateProductItem(productItem);
	}

	@Override
	public ProductItem findProductItemByID(int ID) {
		return productItemRepository.findProductItemByID(ID);
	}
}
