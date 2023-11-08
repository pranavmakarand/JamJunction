package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.pojo.ProductItem;

public class ShoppingCart {
	
	private static List<ProductItem> productItems = new ArrayList<ProductItem>();
	
	private static double myTotal = 0.0;
	
	public static void add(ProductItem item) {
		productItems.add(item);
		updateTotalAmount();
	}

	public static List<ProductItem> getProductItems() {
		return productItems;
	}

	public static double getMyTotal() {
		return myTotal;
	}

	public void setMyTotal(double myTotall) {
		myTotal = myTotall;
	}
	
	public static void updateTotalAmount() {
		myTotal = 0;
		for (ProductItem item : productItems) {
			myTotal += item.getPrice();	
		}
	}
	
	public static void deleteProductItem(int item) {
		productItems.remove(item);
		updateTotalAmount();
	}
	
	public static void removeAllItem() {
		productItems.clear();
	}
}
