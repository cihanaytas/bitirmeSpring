package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ktu.bitirmeproje.data.entity.prod.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query("select p from Product p order by date desc")
	public List<Product> getAllProduct();
	

}
