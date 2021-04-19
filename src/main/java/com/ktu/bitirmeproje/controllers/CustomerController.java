package com.ktu.bitirmeproje.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktu.bitirmeproje.business.dto.StoreDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.StorePoints;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.StoreRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.UserByAuth;


@RestController
public class CustomerController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository prep;
	
	@Autowired
	private UserByAuth uba;
	
	@Autowired
	private StoreRepository srep;
	
	@Autowired
	private UserAccountRepository urep;
	
	@GetMapping("customer/products")
	public List<ProductDto> getAllProduct(){
		
		return productService.getAllProducts();
	}
	
	@GetMapping("customer/products/{nickName}")
	public List<ProductDto> getAllProductByNickname(@PathVariable("nickName") String nickName){	
		return productService.getAllProductByNickname(nickName);
	}
	
	
	@GetMapping("customer/product/{id}")
	public ProductDto getProduct(@PathVariable(name = "id") long productId) {
		ProductDto product =  productService.getProduct(productId);
		return product;
	}
	
	
	
	@PostMapping("customer/pointproduct/{productId}/{point}")
	public void pointproduct(@PathVariable("productId") Long productId,@PathVariable("point") double point) {
		productService.pointProduct(productId, point);
	}
	
	
	@GetMapping("customer/getstoredetail/{storeNickName}")
	public StoreDto getStoreDetail(@PathVariable(name="storeNickName") String storeNickName) {
		Optional<UserAccount> store = urep.findById(storeNickName);
		StoreDetails storeDetail = srep.getStoreDetail(store.get());
System.out.println("geledim");
		StoreDto sdto = new StoreDto();
		sdto.setStore(store.get());
		List<StorePoints> list = storeDetail.getPoints();
		double toplam=0;
		double count=0;
		
		for(StorePoints i : list) {
			toplam+=i.getPoint();
			count++;
		}
		
		sdto.setPoint(toplam/count);
		
		return sdto;

	}
	
	
	
//	@GetMapping("customer/storegor")
//	public Product aasfas() {
////		Optional<StoreDetails> s =  srep.findById((long) 3);
////		StoreDetails store = s.get();
//		
//		Optional<Product> s =  prep.findById((long) 4);
//		Product store = s.get();
//
//		List<PointsOfProduct> list = store.getPoints();
//		
//		for(int i : list) {
//			System.out.println(list.get(0).getPoint());
//		}
//
//		return store;
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
