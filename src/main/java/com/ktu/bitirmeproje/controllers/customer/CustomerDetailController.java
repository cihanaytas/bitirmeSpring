package com.ktu.bitirmeproje.controllers.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktu.bitirmeproje.business.dto.FavouriteProductsDto;
import com.ktu.bitirmeproje.business.service.CustomerService;
import com.ktu.bitirmeproje.data.entity.prod.FavouriteProducts;

@RestController
@RequestMapping("customer")
public class CustomerDetailController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("favourites")
	public List<Long> getFavouriteList(){
		return customerService.getFavouriteList();
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
