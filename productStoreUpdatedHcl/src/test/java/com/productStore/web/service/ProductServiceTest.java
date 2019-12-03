package com.productStore.web.service;

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
import org.springframework.data.domain.Sort;

import com.productStore.model.entities.Product;
import com.productStore.model.exceptions.ProductNotFoundException;
import com.productStore.model.persistance.ProductRepositery;
import com.productStore.model.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {
	@InjectMocks
	ProductServiceImpl prodService;
	
	@Mock
	ProductRepositery prodRepository;
	
	static Product product = new Product();
	static List<Product> products = new ArrayList<Product>();
	
	@BeforeClass
	public static void setup(){
		product.setId(1L);
		product.setName("prod");
		products.add(product);
		System.out.println(products.size());
	}
	
	
	
	@Test
	public void testFindByIdForPositive(){
		Mockito.when(prodRepository.findById(Mockito.any())).thenReturn(Optional.of(product));
		Product product = prodService.findById(2L);
		Assert.assertNotNull(product);
		Assert.assertEquals("prod", product.getName());
	}
	
	@Test
	public void testFindByNameForPositive(){
		Mockito.when(prodRepository.findByName(Mockito.any())).thenReturn(product);
		Product product = prodService.findByName("prod");
		Assert.assertNotNull(product);
		Assert.assertEquals("prod", product.getName());
	}

	
//	@Test
//	public void testFindByIdForNegative(){
//		Mockito.when(prodRepository.findById(2L)).thenReturn(Optional.of(product));
//		Product product = prodService.findById(5L);
//		Assert.assertNull(product);
//	}

	@Test(expected=ProductNotFoundException.class)
	public void testFindByIdForExce(){
		Mockito.when(prodRepository.findById(2L)).thenReturn(Optional.of(product));
		prodService.findById(5L);
	}
	
	@Test
	public void testFindByNameForNegative(){
		Mockito.when(prodRepository.findByName("prod")).thenReturn(product);
		Product prod=prodService.findByName("prid");
		Assert.assertNull(prod);
	}
		
	@Test
	public void testGetAllProducts(){
		Mockito.when(prodRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(products);
		
		List<Product> restProducts = prodService.findAll();
		Assert.assertNotNull(restProducts);
		//Assert.assertEquals(1, restProducts.size());
	}
}
