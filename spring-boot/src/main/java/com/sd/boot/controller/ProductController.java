package com.sd.boot.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sd.boot.model.Product;

@RestController
public class ProductController {

	private static Map<String, Product> productRepo = new HashMap<String, Product>();
	   static {
	      Product honey = new Product();
	      honey.setId("1");
	      honey.setName("Honey");
	      productRepo.put(honey.getId(), honey);
	      
	      Product almond = new Product();
	      almond.setId("2");
	      almond.setName("Almond");
	      productRepo.put(almond.getId(), almond);
	   }
	   
	   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	   public ResponseEntity<String> delete(@PathVariable("id") String id) { 
	      productRepo.remove(id);
	      return new ResponseEntity<String>("Product is deleted successsfully", HttpStatus.OK);
	   }
	   
	   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<String> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
	      productRepo.remove(id);
	      product.setId(id);
	      productRepo.put(id, product);
	      return new ResponseEntity<String>("Product is updated successsfully", HttpStatus.OK);
	   }
	   
	   @RequestMapping(value = "/products", method = RequestMethod.POST)
	   public ResponseEntity<String> createProduct(@RequestBody Product product) {
	      productRepo.put(product.getId(), product);
	      return new ResponseEntity<String>("Product is created successfully", HttpStatus.CREATED);
	   }
	   
	   @RequestMapping(value = "/products")
	   public ResponseEntity<Collection<Product>> getProduct() {
	      return new ResponseEntity<Collection<Product>>(productRepo.values(), HttpStatus.OK);
	   }
}
