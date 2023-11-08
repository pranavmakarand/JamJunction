package com.example.demo.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="productType")
public class ProductType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "productTypeId")
	private int productTypeId;
	
	@Column(name="productTypeName")
	private String productTypeName;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="productType",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	List<ProductItem> items = new ArrayList<ProductItem>();
	
	public ProductType() {
		
	}

	public int getId() {
		return this.productTypeId;
	}

	public void setId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return this.productTypeName;
	}

	public void setName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	public void addProductItem(ProductItem items) {
		
		this.items.add(items);
		
		items.setProductType(this);
	}
	
	public List<ProductItem> getProductItem() {
		return this.items;
	}
}