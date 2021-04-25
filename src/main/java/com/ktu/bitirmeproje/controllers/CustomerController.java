package com.ktu.bitirmeproje.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ktu.bitirmeproje.data.entity.prod.*;
import com.ktu.bitirmeproje.data.repository.MyProductRepository;
import com.ktu.bitirmeproje.data.repository.StoreRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;

@RestController
public class CustomerController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private StoreRepository srep;
	
	@Autowired
	private UserAccountRepository urep;
	
	@Autowired
	private MyProductRepository proRep;

	
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
	
	@GetMapping("customer/plist/{page}")
	public ResponseEntity<List<ProductDto>> getplist(@PathVariable(name="page") Integer page) {
		 List<ProductDto> list = productService.getAllEmployees(page, 10, "date");

	        return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
