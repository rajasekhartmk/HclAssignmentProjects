package com.productStore.model.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productStore.model.entities.Customer;

@Repository
public interface CustomerRepositery extends JpaRepository<Customer, Long> {

	public List<Customer> findByNameContaining(String name);
	
//	public List<Customer> findAllByProductName(String name);
}
