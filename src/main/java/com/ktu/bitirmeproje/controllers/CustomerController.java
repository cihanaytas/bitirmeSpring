package com.ktu.bitirmeproje.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ktu.bitirmeproje.business.dto.StoreDto;
import com.ktu.bitirmeproje.business.dto.prod.CartItemDto;
import com.ktu.bitirmeproje.business.dto.prod.CartsProductsDto;
import com.ktu.bitirmeproje.business.dto.prod.CommentProductDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.dto.prod.ShoppingDto;
import com.ktu.bitirmeproje.business.impl.SendMail;
import com.ktu.bitirmeproje.business.impl.apriori.Apriori;
import com.ktu.bitirmeproje.business.service.CustomerService;
import com.ktu.bitirmeproje.business.service.PasswordService;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.business.service.ShoppingService;
import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.StorePoints;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.AprioriOranRepository;
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
	private CustomerService customerService;
	
	@Autowired
	private ShoppingService shopService;
	

	
	
//	@GetMapping("customer/product/{id}")
//	public ProductDto getProduct(@PathVariable(name = "id") long productId) {
//		ProductDto product =  productService.getProduct(productId);
//		return product;
//	}
	

	

	
	
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
	

	
	
//	@GetMapping("customer/plist/{category}/{page}")
//	public ResponseEntity<List<ProductDto>> getphonelist(@PathVariable(name="page") Integer page,@PathVariable(name="category") String category) {
//		 List<ProductDto> list = productService.getAllProductByCategory(page, 10, "date",category);
//
//	     return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
//	}
	

	
	

	
	@PostMapping("customer/sales")
	public void sales(@RequestBody List<CartsProductsDto> cartList) throws IOException {
		customerService.sales(cartList);

	}
	
	
	@GetMapping("customer/shoppinglist")
	public ResponseEntity<List<ShoppingDto>> getShoppingList(){
		List<ShoppingDto> list = shopService.getShoppingList();
		if(list.isEmpty()) {
			return new ResponseEntity<List<ShoppingDto>>(null,new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ShoppingDto>>(list,new HttpHeaders(), HttpStatus.OK);

	}
	
	
	@GetMapping("customer/getcartitem/{shoppingId}")
	public ResponseEntity<List<CartItemDto>> getCartItemList(@PathVariable(name="shoppingId") Long shoppingId){
		List<CartItemDto> list = shopService.getCartItemList(shoppingId);
		if(list.isEmpty()) {
			return new ResponseEntity<List<CartItemDto>>(null,new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CartItemDto>>(list,new HttpHeaders(), HttpStatus.OK);
		
	}
	
	

	
	

 
	

	@Autowired
	private Apriori apriori;
	
	@Autowired
	private AprioriOranRepository apRep;
	
	@GetMapping("customer/testapriori")
	public void asfd() throws IOException {
		apriori.fun();
	}
	
	@GetMapping("customer/testapriori2")
	public int assfd() {
		return apRep.exist("s??t", "ekmek");
	}
	
	




	
	
	
	
	
	
	
	
	
	
	
}
