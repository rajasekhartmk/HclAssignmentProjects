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
import com.productStore.model.entities.Order;
import com.productStore.model.exceptions.CustomerNotFoundException;
import com.productStore.model.persistance.CustomerRepositery;
import com.productStore.model.persistance.OrderRepositery;
import com.productStore.model.service.OrderServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest {
	@InjectMocks
	OrderServiceImpl ordService;
	
	@Mock
	OrderRepositery ordRepository;
	
	@Mock
	CustomerRepositery custRepo;
	
	static Customer customer=new Customer();
	static Order order = new Order();
	static List<Order> orders = new ArrayList<Order>();
	
	@BeforeClass
	public static void setup(){
		customer.setId(1L);
		order.setId(1L);
		order.setCustomerName("raj");
		order.setProductName("phone");
		order.setCustomer(customer);
		orders.add(order);
		customer.addOrder(order);
	}
	
	@Test
	public void testFindByCustomerNamePositive(){
		Mockito.when(ordRepository.findByCustomerName(Mockito.any())).thenReturn(orders);
		List<Order> testOrders=ordService.findByCustomerName("raj");
		Assert.assertNotNull(testOrders);
		Assert.assertEquals(1, testOrders.size());
		
	}
	
	@Test
	public void testFindByCustomerNameNegative(){
		Mockito.when(ordRepository.findByCustomerName("raj")).thenReturn(orders);
		List<Order> testOrders=ordService.findByCustomerName("ra");
		//Assert.assertNull(testOrders);
		Assert.assertEquals(0, testOrders.size());
		
	}
	
	@Test
	public void testFindByProductNameContainingPositive(){
		Mockito.when(ordRepository.findByProductNameContaining("ph")).thenReturn(orders);
		List<Order> testOrders=ordService.findByProductNameContaining("ph");
		Assert.assertNotNull(testOrders);
		Assert.assertEquals(1, testOrders.size());
	}
	
	@Test
	public void testFindByProductNameContainingNegative(){
		Mockito.when(ordRepository.findByProductNameContaining("ph")).thenReturn(orders);
		List<Order> testOrders=ordService.findByProductNameContaining("hp");
		//Assert.assertNotNull(testOrders);
		Assert.assertEquals(0, testOrders.size());
	}
	
	@Test
	public void testFindByCustomerIdPositive(){
		Mockito.when(custRepo.findById(1L)).thenReturn(Optional.of(customer));
		List<Order> testOrders=ordService.findByCustomerId(1L);
		Assert.assertNotNull(testOrders);
		Assert.assertEquals(1, testOrders.size());
	}
	
//	@Test
//	public void testFindByCustomerIdNegative(){
//		Mockito.when(custRepo.findById(1L)).thenReturn(Optional.of(customer));
//		List<Order> testOrders=ordService.findByCustomerId(2L);
//		//Assert.assertNotNull(testOrders);
//		Assert.assertEquals(0, testOrders.size());
//	}
	
	@Test(expected=CustomerNotFoundException.class)
	public void testFindByCustomerIdException(){
		Mockito.when(custRepo.findById(1L)).thenReturn(Optional.of(customer));
		ordService.findByCustomerId(2L);
		//Assert.assertNotNull(testOrders);
		//Assert.assertEquals(1, testOrders.size());
	}
	
	@Test
	public void testFindAll(){
		Mockito.when(ordRepository.findAll()).thenReturn(orders);
		List<Order> testOrders=ordService.findAll();
		Assert.assertNotNull(testOrders);
		Assert.assertEquals(1, testOrders.size());
	}
}
