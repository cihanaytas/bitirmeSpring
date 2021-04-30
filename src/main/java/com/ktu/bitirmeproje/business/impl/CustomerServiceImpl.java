package com.ktu.bitirmeproje.business.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktu.bitirmeproje.business.dto.prod.CartsProductsDto;
import com.ktu.bitirmeproje.business.service.CustomerService;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.CartsProducts;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;
import com.ktu.bitirmeproje.data.repository.CustomerRepository;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.utils.UserByAuth;


//@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserByAuth authService;
	
	@Override
	public void sales(List<CartsProductsDto> cartListDto) {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		
		//shopping
		Shopping shopping = new Shopping();
		Date date = new Date();
		shopping.setDate(date);
		shopping.setCustomer(customer);
				
		List<CartsProducts> cartList = new ArrayList<CartsProducts>();
		for(CartsProductsDto cartDto : cartListDto) {
			CartsProducts cart = new CartsProducts();
			converToEntityCartProducts(cart, cartDto);
			cartList.add(cart);
		}		

		
		for(CartsProducts cart : cartList) {
			shopping.getProducts().add(cart);
		}
		//shopping
		
		
		customer.getShoppingList().add(shopping);
		
		
		customerRepository.save(customer);
		
		
		
	}
	
	
	
	private void converToEntityCartProducts(CartsProducts cart, CartsProductsDto cartDto) {
		cart.setQuantity(cartDto.getQuantity());
		Optional<Product> product = productRepository.findById(cartDto.getProductId());
		cart.setProduct(product.get());
		
	}
	

 

	
	
	
	



}
