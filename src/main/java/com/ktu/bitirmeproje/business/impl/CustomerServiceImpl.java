package com.ktu.bitirmeproje.business.impl;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.FavouriteProductsDto;
import com.ktu.bitirmeproje.business.dto.prod.CartsProductsDto;
import com.ktu.bitirmeproje.business.impl.apriori.Apriori;
import com.ktu.bitirmeproje.business.service.CustomerService;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.CartsProducts;
import com.ktu.bitirmeproje.data.entity.prod.FavouriteProducts;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;
import com.ktu.bitirmeproje.data.repository.CustomerRepository;
import com.ktu.bitirmeproje.data.repository.FavouriteProductsRepository;
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
	
	@Autowired
	private Apriori apriori;
	
	@Autowired
	private FavouriteProductsRepository favRep;
 
	
	@Override
	public void sales(List<CartsProductsDto> cartListDto) throws IOException {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		
		//shopping
		Shopping shopping = new Shopping();
		Date date = new Date();
		shopping.setDate(date);
		shopping.setCustomer(customer);
		
		double totalPrice = 0;
					
		List<CartsProducts> cartList = new ArrayList<CartsProducts>();
		for(CartsProductsDto cartDto : cartListDto) {
			CartsProducts cart = new CartsProducts();
			totalPrice+= cartDto.getPrice() * cartDto.getQuantity();
			converToEntityCartProducts(cart, cartDto);
			cartList.add(cart);
		}
		
		shopping.setTotalPrice(totalPrice);

		
		for(CartsProducts cart : cartList) {
			shopping.getProducts().add(cart);
		}
		
		
		customer.getShoppingList().add(shopping);
		
		addToFile(cartListDto);
		
		apriori.fun();
				
		customerRepository.save(customer);
		
		
		
	}
	
	
	@Override
	public List<Long> getFavouriteList() {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		List<Long> favDtoList = new ArrayList<Long>();
		
		for(FavouriteProducts fav : customer.getFavourites()) {
			FavouriteProductsDto favDto = new FavouriteProductsDto();
			convertToDto(fav,favDto);
			favDtoList.add(favDto.getProductId());
		}
		
		return favDtoList;
	}
	
	
	@Override
	public void addFavourite(Long productId) {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		Optional<Product> p = productRepository.findById(productId);
		FavouriteProducts fav = new FavouriteProducts();
		fav.setCustomer(customer);
		fav.setProduct(p.get());
		customer.getFavourites().add(fav);
		
		customerRepository.save(customer);
	}
	
	@Override
	@Transactional
	public void deleteFavourite(Long productId) {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		Optional<Product> p = productRepository.findById(productId);
		FavouriteProducts fav = favRep.getFav(p.get(), customer);
		favRep.deleteById(fav.getID());

	}

	
	
	
	private void converToEntityCartProducts(CartsProducts cart, CartsProductsDto cartDto) {
		cart.setQuantity(cartDto.getQuantity());
		Optional<Product> product = productRepository.findById(cartDto.getProductId());
		cart.setProduct(product.get());
		
	}
	
	private void addToFile(List<CartsProductsDto> cartListDto) {
        try(FileWriter fw = new FileWriter("C:\\Users\\cihanaytas\\Desktop\\ccc.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {String line="";
            List<String> categories = new ArrayList<String>();
        	for(int i=0; i<cartListDto.size(); i++) {
        		if(categories.contains(cartListDto.get(i).getCategory())) {
        			
        		}else {
        			categories.add(cartListDto.get(i).getCategory());
                	line+=cartListDto.get(i).getCategory();}
                if(i!=cartListDto.size()-1) {
                	line+=",";
                }
                }
        	out.println(line);
            }

        	catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
	}
	
	private void convertToDto(FavouriteProducts fav, FavouriteProductsDto favDto) {
		favDto.setCustomerNickName(fav.getCustomer().getCustomer().getNickName());
		favDto.setID(fav.getID());
		favDto.setProductId(fav.getProduct().getProductID());
		
	}










 

	
	
	
	



}
