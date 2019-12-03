package com.productStore.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productStore.model.entities.Order;
import com.productStore.model.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(path="/order",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrders(){
		return new ResponseEntity<List<Order>>(orderService.findAll(),HttpStatus.FOUND);
	}
	
	@GetMapping(path="/order/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrdersByProduct(@PathVariable(name="name")String name){
		return new ResponseEntity<List<Order>>(orderService.findByProductNameContaining(name),HttpStatus.FOUND);
	}
	
	@GetMapping(path="/order/customer/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrdersByCustomer(@PathVariable(name="name")String name){
		return new ResponseEntity<List<Order>>(orderService.findByCustomerName(name),HttpStatus.FOUND);
	}
}
