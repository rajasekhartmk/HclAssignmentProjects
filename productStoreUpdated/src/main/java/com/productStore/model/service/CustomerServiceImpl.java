package com.productStore.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productStore.model.entities.Customer;
import com.productStore.model.exceptions.CustomerNotFoundException;
import com.productStore.model.persistance.CustomerRepositery;
import com.productStore.model.persistance.ProductRepositery;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerRepositery repo;
	
	@Autowired
	private ProductRepositery productRepo;
	
	@Override
	public List<Customer> findAll() {
		return repo.findAll();
	}

	@Override
	public Customer findById(Long id) {
		return repo.findById(id).orElseThrow(CustomerNotFoundException::new);
	}

	@Override
	public void createCustomer(Customer customer) {
		repo.save(customer);
	}

	@Override
	public void deleteCustomerById(long id) {
		repo.deleteById(id);
	}

	@Override
	public void update(long id, Customer customer) {
		Customer customerUpdate=repo.findById(id).orElseThrow(CustomerNotFoundException::new);
		repo.save(customerUpdate);
	}

	@Override
	public List<Customer> findByNameContaining(String name) {
		return repo.findByNameContaining(name);
	}

//	@Override
//	public List<Customer> findAllByProductName(String name) {
//		Product product=productRepo.findByName(name);
//		return repo.findAllByProductName(product.getName());
//	}
	
	
}
