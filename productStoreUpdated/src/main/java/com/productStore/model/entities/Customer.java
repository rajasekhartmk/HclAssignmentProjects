package com.productStore.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="customer_tab4")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
//	public String productName;
//	private double productRating;
//	public String storeName;
//	private double storeRating;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Order> orders=new ArrayList<>();

	public Customer() {
	}

	public void addOrder(Order order){
		orders.add(order);
		order.setCustomer(this);
	}

	public Customer(String name, String phone/*, String productName,
			double productRating, String storeName, double storeRating*/) {
	
		this.name = name;
		this.phone = phone;
//		this.productName = productName;
//		this.productRating = productRating;
//		this.storeName = storeName;
//		this.storeRating = storeRating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

//	public String getProductName() {
//		return productName;
//	}
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
//
//	public double getProductRating() {
//		return productRating;
//	}
//
//	public void setProductRating(double productRating) {
//		this.productRating = productRating;
//	}
//
//	public String getStoreName() {
//		return storeName;
//	}
//
//	public void setStoreName(String storeName) {
//		this.storeName = storeName;
//	}
//
//	public double getStoreRating() {
//		return storeRating;
//	}
//
//	public void setStoreRating(double storeRating) {
//		this.storeRating = storeRating;
//	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	
	
}
