package com.ktu.bitirmeproje.controllers.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.CustomerService;


@RestController
@RequestMapping("customer")
public class CustomerDetailController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("favourites")
	public List<Long> getFavouriteList(){
		return customerService.getFavouriteList();
	}
	
	@GetMapping("favouritesproducts/{page}")
	public ResponseEntity<List<ProductDto>> getFavouriteProductsList(@PathVariable(name="page") Integer page){
		List<ProductDto> list = customerService.getFavouriteProductsList(page,5);
				// productService.search(page, 10, "price",category);
	    return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
		//return customerService.getFavouriteProductsList();
	}
	
	@PostMapping("addfavourite/{productId}")
	public void addFavourite(@PathVariable(name="productId") Long productId) {
		customerService.addFavourite(productId);
	}
	
	@DeleteMapping("deletefavourite/{productId}")
	public void deleteFavourite(@PathVariable(name="productId") Long productId) {
		customerService.deleteFavourite(productId);
	}
	
}
