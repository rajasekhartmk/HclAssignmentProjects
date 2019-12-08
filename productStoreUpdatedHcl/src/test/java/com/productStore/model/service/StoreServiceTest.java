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

import com.productStore.model.entities.Store;
import com.productStore.model.exceptions.StoreNotFoundException;
import com.productStore.model.persistance.StoreRepositery;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StoreServiceTest {

	@InjectMocks
	StoreServiceImpl storeService;
	
	@Mock
	StoreRepositery storeRepository;
	
	static Store store = new Store();
	static List<Store> stores = new ArrayList<Store>();
	
	@BeforeClass
	public static void setup(){
		store.setId(1L);
		store.setName("store");
		stores.add(store);
	}
	
	@Test
	public void testFindByIdForPositive(){
		Mockito.when(storeRepository.findById(Mockito.any())).thenReturn(Optional.of(store));
		Store store = storeService.findById(1L);
		Assert.assertNotNull(store);
		Assert.assertEquals("store", store.getName());
	}
	
	@Test(expected=StoreNotFoundException.class)
	public void testFindByIdForExce(){
		Mockito.when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
		storeService.findById(2L);
	}
	
//	@Test
//	public void testFindAllByIdForPositive(){
//		Mockito.when(storeRepository.findAllById(Mockito.any())).thenReturn(stores);
//		List<Store> testStores = storeService.findAllById(1L);
//		Assert.assertNotNull(testStores);
//		Assert.assertEquals(1, testStores.size());
//	}
	
	@Test
	public void testFindAllByIdForNegative(){
		Mockito.when(storeRepository.findAllById(1L)).thenReturn(stores);
		List<Store> testStores = storeService.findAllById(2L);
		//Assert.assertNotNull(testStores);
		Assert.assertEquals(0, testStores.size());
	}
	
	@Test
	public void testFindAll(){
		Mockito.when(storeRepository.findAll()).thenReturn(stores);
		List<Store> testStores=storeService.findAll();
		Assert.assertNotNull(testStores);
		Assert.assertEquals(1, testStores.size());
	}
}
