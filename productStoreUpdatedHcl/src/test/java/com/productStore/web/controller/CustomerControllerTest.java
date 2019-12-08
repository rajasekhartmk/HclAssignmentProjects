package com.productStore.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.productStore.model.entities.Customer;
import com.productStore.model.service.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerControllerTest {

	@Mock
	CustomerServiceImpl custService;
	
	@InjectMocks
	CustomerController custController;
	
	static Customer customer=new Customer();
	static List<Customer> customers=new ArrayList<Customer>();
	
	@BeforeClass
	public static void setup(){
		customer.setId(1L);
		customer.setEmail("email");
		customer.setName("raj");
		customers.add(customer);
	}
	
	@Test
	public void testGetAllCustomers(){
		//Mockito.when(custController.getAllCustomers()).thenReturn(HttpStatus.OK);
		
	}
}
