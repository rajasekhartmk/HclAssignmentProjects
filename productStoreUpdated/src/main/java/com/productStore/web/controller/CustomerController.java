package com.productStore.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productStore.model.entities.Customer;
import com.productStore.model.entities.Order;
import com.productStore.model.entities.Product;
import com.productStore.model.entities.Store;
import com.productStore.model.exceptions.CustomerNotFoundException;
import com.productStore.model.service.CustomerService;
import com.productStore.model.service.OrderService;
import com.productStore.model.service.ProductService;
import com.productStore.model.service.StoreService;
import com.productStore.web.controller.requestBean.MessageRequest;
import com.productStore.web.controller.requestBean.RegRequest;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService custService;
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path="/customer",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return new ResponseEntity<List<Customer>>(custService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/customer/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getAllCustByName(@PathVariable(name="name")String name){
		return new ResponseEntity<List<Customer>>(custService.findByNameContaining(name),HttpStatus.FOUND);
	}
	
//	@GetMapping(path="/customer/product/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Customer>> getAllCustByProduct(@PathVariable(name="name")String name){
//		return new ResponseEntity<List<Customer>>(custService.findAllByProductName(name),HttpStatus.FOUND);
//	}
	
	@PostMapping(path="/customer/reg",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageRequest> getLoginMesssage(@RequestBody RegRequest req){
		Customer customer=new Customer(req.getName(), req.getPassword());
		custService.createCustomer(customer);
		MessageRequest request=new MessageRequest("registration successfull go to login");
		return ResponseEntity.ok().body(request);
	}
	
	
	@PostMapping(path="/customer/login",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> login(@RequestBody RegRequest request){
		Long id=request.getProdId();
		Long id1=request.getStoreId();
		
		Product product=prodService.findById(id);
		Store store=storeService.findById(id1);
		
		Customer customer=null;
		List<Customer> list=custService.findAll();
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).getPhone().equals(request.getPassword())){
				customer=list.get(i);
			}
			else{
				throw new CustomerNotFoundException();
			}
		}
		
		//find order
		Order order=new Order(product.getName(), product.getCompany(), customer.getName(), product.getPrice(), store.getName());
		customer.addOrder(order);
		order.setCustomer(customer);
		orderService.createOrder(order);
		
			
		
		return new ResponseEntity<List<Customer>>(custService.findAll(),HttpStatus.OK);
		
	}
	
	
}
