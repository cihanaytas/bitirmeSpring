package com.ktu.bitirmeproje.controllers.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ktu.bitirmeproje.business.dto.prod.CommentProductDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;

@RestController
@RequestMapping("customer")
public class CustomerProductController {

	@Autowired
	private ProductService productService;

	
	@Cacheable(cacheNames = "mySpecialCache")
	@GetMapping("products")
	public List<ProductDto> getAllProduct(){
		
		return productService.getAllProducts();
	}
	

	//@Cacheable(cacheNames = "mySpecialCache")
	@GetMapping("products/{nickName}")
	public List<ProductDto> getAllProductByNickname(@PathVariable("nickName") String nickName){	
		return productService.getAllProductByNickname(nickName);
	}

    //@Cacheable(value = "product",key = "#p0")
    //@Cacheable(cacheNames = "mySpecialCache")
	@GetMapping("product/{id}")
	public List<ProductDto> getProduct(@PathVariable(name = "id") long productId) {
		return productService.getProductOran(productId);
	}
	
 
	
	
	@PostMapping("pointproduct/{productId}/{point}")
	public void pointproduct(@PathVariable("productId") Long productId,@PathVariable("point") double point) {
		productService.pointProduct(productId, point);
	}
	
	@GetMapping("plist/{page}")
	public ResponseEntity<List<ProductDto>> getplist(@PathVariable(name="page") Integer page) {
		 List<ProductDto> list = productService.getAllProducts(page, 10, "date",true);

	        return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@GetMapping("plist/{category}/{page}")
	public ResponseEntity<List<ProductDto>> getphonelisst(@PathVariable(name="page") Integer page,@PathVariable(name="category") String category) {
		 List<ProductDto> list = productService.search(page, 10, "date",category);

	     return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("plist/pricedesc/{page}")
	public ResponseEntity<List<ProductDto>> getAllProductByPricedesc(@PathVariable(name="page") Integer page){
		
		 List<ProductDto> list = productService.getAllProducts(page, 10, "price",true);
		 return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("plist/priceasc/{page}")
	public ResponseEntity<List<ProductDto>> getAllProductByPriceasc(@PathVariable(name="page") Integer page){
		
		 List<ProductDto> list = productService.getAllProducts(page, 10, "price",false);
		 return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}


	@PostMapping("addcomment")
	public boolean addComment(@RequestBody CommentProductDto comment) {
		return productService.addComment(comment);		
	}
	
	@PutMapping("updatecomment")
	public boolean updateComment(@RequestBody CommentProductDto comment) {
		return productService.addComment(comment);		
	}
	
	
	@GetMapping("getcommentlist/{productId}")
	public ResponseEntity<List<CommentProductDto>> getCommentList(@PathVariable(name="productId") Long productId){
		List<CommentProductDto> list = productService.getCommentList(productId);
		
		if(list.isEmpty()) {
			return new ResponseEntity<List<CommentProductDto>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CommentProductDto>>(list,new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@DeleteMapping("deletecomment/{commentId}")
	public String deleteComment(@PathVariable(name="commentId") Long commentId) {
		productService.deleteComment(commentId);
		return "silindi";		

	}
	
	//bir kategoriyi ücrete göre listeler
	@GetMapping("plist/categorybyprice/{category}/{page}")
	public ResponseEntity<List<ProductDto>> getCategoryByPrice(@PathVariable(name="page") Integer page,@PathVariable(name="category") String category) {
		 List<ProductDto> list = productService.search(page, 10, "price",category);

	     return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("plist/priceRange/{min}/{max}/{page}")
	public ResponseEntity<List<ProductDto>> priceRange(@PathVariable(name="page") Integer page,@PathVariable(name="min") Long min,@PathVariable(name="max") Long max){
		 List<ProductDto> list = productService.priceRange(page, 10, min, max);

	     return new ResponseEntity<List<ProductDto>>(list, new HttpHeaders(), HttpStatus.OK);	
		
	}
 

 	@GetMapping("plistt/{categorylist}")
	public ResponseEntity<List<ProductDto>> getProductListByCategoryList(@PathVariable(name="categorylist") List<String> categorylist){

	System.out.println(categorylist);

		List<ProductDto> listt = productService.getProductListByCategoryList(0, 10, categorylist);

		return new ResponseEntity<List<ProductDto>>(listt, new HttpHeaders(), HttpStatus.OK);
	}
	
}
