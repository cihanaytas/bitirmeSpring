package com.ktu.bitirmeproje.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktu.bitirmeproje.business.dto.prod.NotificationProductDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.business.service.StoreService;


@RestController
@RequestMapping("store")
public class StoreProductController {


	@Autowired
	private ProductService productService;
	
	@Autowired
	private StoreService storeService;
		
	
	@PostMapping("/addproduct")
	public String addProduct(@Valid @RequestBody ProductDto productDto) {
		productService.addProduct(productDto);
		return "a";
	}
	
	@PutMapping("/addproduct/{productId}")
	public String updateProduct(@PathVariable(name="productId") Long productId,@Valid @RequestBody ProductDto productDto) {

		productService.updateProduct(productId, productDto);
		return "a";
	}
	
	@GetMapping("/myproducts/{page}")
	public ResponseEntity<List<ProductDto>> myProducts(@PathVariable(name="page") Integer page){
		 List<ProductDto> list = productService.getAllProductByStore(page, 10);

	     return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ProductDto getProduct(@PathVariable(name = "id") long productId) {
		ProductDto product =  productService.getProduct(productId);
		return product;
	}
	
	@GetMapping("/notifications")
	public ResponseEntity<List<NotificationProductDto>> getNotifications(){
		List<NotificationProductDto> list = storeService.getNotifications();
		
		return new ResponseEntity<List<NotificationProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/notification/{notificationID}/{onay}")
	public void notificationConfirmation(@PathVariable(name="notificationID") Long notificationID, @PathVariable(name="onay") Boolean onay){
		storeService.notificationConfirmation(notificationID,onay);
	}
	
	
}
