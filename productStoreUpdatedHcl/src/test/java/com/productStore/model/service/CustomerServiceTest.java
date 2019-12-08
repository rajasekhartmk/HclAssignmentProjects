package com.productStore.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.productStore.model.entities.Customer;
import com.productStore.model.exceptions.CustomerNotFoundException;
import com.productStore.model.persistance.CustomerRepositery;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceTest {

	@InjectMocks
	CustomerServiceImpl custService;
	
	@Mock
	CustomerRepositery custRepository;
	
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
	public void testFindByEmailPositive(){
		Mockito.when(custRepository.findByEmail(Mockito.any())).thenReturn(customer);
		Customer cust=custService.findByEmail("email");
		Assert.assertNotNull(cust);
		Assert.assertEquals("raj", cust.getName());
	}
	
	@Test
	public void testFindByEmailNegative(){
		Mockito.when(custRepository.findByEmail("email")).thenReturn(customer);
		Customer cust=custService.findByEmail("emai");
		Assert.assertNull(cust);
		//Assert.assertEquals("raj", cust.getName());
	}
	
	@Test
	public void testFindByIdPositive(){
		Mockito.when(custRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));
		Customer cust=custService.findById(1L);
		Assert.assertNotNull(cust);
		Assert.assertEquals("raj", cust.getName());
	}
	
	@Test(expected=CustomerNotFoundException.class)
	public void testFindByIdNegativeException(){
		Mockito.when(custRepository.findById(1L)).thenReturn(Optional.of(customer));
		custService.findById(2L);
	}
	
	@Test
	public void testCreateCustomer(){
		Customer customer2=new Customer();
		customer2.setId(2L);
		customer2.setEmail("email");
		customer2.setName("king");
		List<Customer> testcusts=customers;
		testcusts.add(customer2);
		
		Mockito.when(custRepository.save(customer2)).thenReturn(customer2);
		custService.createCustomer(customer2);
		Assert.assertNotNull(customer2);
		Assert.assertEquals("king", customer2.getName());
		Assert.assertEquals(2, testcusts.size());
	}
	
//	@Test
//	public void testDeleteById(){
//		Mockito.when(custRepository.findAll()).thenReturn(customers);
//		Mockito.when(custRepository.deleteById(2L);
//		List<Customer> testcusts=custService.findAll();
//		custService.deleteById(2L);
//		Assert.assertEquals(1, testcusts.size());
//	}
	
	@Test
	public void testFindAll(){
		Mockito.when(custRepository.findAll()).thenReturn(customers);
		List<Customer> testcusts=custService.findAll();
		Assert.assertNotNull(testcusts);
		Assert.assertEquals(1, testcusts.size());
	}
}
