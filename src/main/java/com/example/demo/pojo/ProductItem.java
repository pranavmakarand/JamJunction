package com.example.demo.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="productItem")
public class ProductItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "productItemId")
	private int productItemId;
	
	@Column(name="productItemName")
	private String productItemName;
	
	@Column(name="productItemPrice")
	private double productItemPrice;
	
	@Column(name="productItemCount")
	private int count;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, 
		CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="productTypeId")
	private ProductType productType;
	
	public ProductItem() {
		super();
	}

	public int getId() {
		return this.productItemId;
	}

	public void setId(int productItemId) {
		this.productItemId = productItemId;
	}

	public String getName() {
		return this.productItemName;
	}

	public void setName(String productItemName) {
		this.productItemName = productItemName;
	}

	public double getPrice() {
		return this.productItemPrice;
	}

	public void setPrice(double productItemPrice) {
		this.productItemPrice = productItemPrice;
	}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
}