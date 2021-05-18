package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.prod.FavouriteProducts;
import com.ktu.bitirmeproje.data.entity.prod.Product;

@Repository
public interface FavouriteProductsRepository extends CrudRepository<FavouriteProducts, Long>{

	@Query("select f from FavouriteProducts f where f.product= :product and f.customer= :customer")
	public FavouriteProducts getFav(@Param("product") Product product, @Param("customer") CustomerDetails customer);
}
