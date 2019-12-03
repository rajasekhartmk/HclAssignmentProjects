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

import com.productStore.model.entities.Order;
import com.productStore.model.entities.Product;
import com.productStore.model.entities.Store;
import com.productStore.model.service.CustomerService;
import com.productStore.model.service.OrderService;
import com.productStore.model.service.ProductService;
import com.productStore.model.service.StoreService;
import com.productStore.web.controller.requestBean.MessageRequest;
import com.productStore.web.controller.requestBean.RatingRequest;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService prodService;
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path="/product",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<List<Product>>(prodService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/product/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getByNameContaining(@PathVariable(name="name")String name){
		return new ResponseEntity<List<Product>>(prodService.findByNameContaining(name),HttpStatus.OK);
	}
	
//	@GetMapping(path="/product/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Store>> getStores(@PathVariable(name="id")Long id){
//		return new ResponseEntity<List<Store>>(prodService.findById(id).getStores(),HttpStatus.OK);
//	}
	@GetMapping(path="/product/{id}/stores",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Store>> getstoresMesssage(@PathVariable(name="id")Long id){
		Product product=prodService.findById(id);
		return new ResponseEntity<List<Store>>(product.getStores(),HttpStatus.OK);
	}
	@GetMapping(path="/product/{id}/stores/{id1}/buy",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageRequest> getLoginMesssage(@PathVariable(name="id")Long id,@PathVariable(name="id1")Long id1){
		Product product=prodService.findById(id);
		Store store=storeService.findById(id1);
		MessageRequest request=new MessageRequest("product: " +product.getName()+" of company name: "+product.getCompany()+" with price: "+product.getPrice()+" from store: "+store.getName()+" Login to proceed");
		return ResponseEntity.ok().body(request);
	}
	@PostMapping(path="/product/{id}/rating",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> productRating(@RequestBody RatingRequest req,@PathVariable(name="id")Long id){
		Product product=prodService.findById(id);
		int count=0;
		//order
		List<Order> orders=orderService.findAll();
		for(int i=0;i<orders.size();i++){
			if(orders.get(i).getProductName().equals(product.getName())){
				count=count+1;
			}
		}
		
		product.setRating(((product.getRating()*count)+req.getRating())/(count+1));
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		
	}
	
	
	
//	@GetMapping(path="/product/{name}/{id}/{id1}",produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> getLoginMesssage(HttpServletRequest request, HttpServletResponse response,@PathVariable(name="name")String name,@PathVariable(name="id")Long id,@PathVariable(name="id1")Long id1){
//		try {
//			response.sendRedirect("http://localhost:8094/store/api/customer");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>(HttpStatus.OK);
//	}
	
}
