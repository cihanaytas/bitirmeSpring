package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query("select p from Product p order by date desc")
	public List<Product> getAllProduct();
	
	@Query("select p from Product p where p.store= :store  order by date desc")
	public List<Product> getAllProductByNickname(@Param("store") UserAccount store);
	
	@Query("select count(*) from Product p where p.store= :store and p.brand= :brand and p.model= :model")
	public int isExist(@Param("store") UserAccount store, @Param("brand") String brand, @Param("model") String model);
	
	@Query(value="SELECT * FROM Product where category= :category AND productID!= :productId ORDER BY RAND ()  LIMIT :limit", nativeQuery = true)
	public List<Product> randomRows(@Param("category") String category, @Param("productId") Long productId, @Param("limit") int limit);
	
	@Query(value = "SELECT * FROM Product where category= :category  ORDER BY RAND () LIMIT 1", nativeQuery = true)
	public Product randomRow(@Param("category") String category);
	


}
