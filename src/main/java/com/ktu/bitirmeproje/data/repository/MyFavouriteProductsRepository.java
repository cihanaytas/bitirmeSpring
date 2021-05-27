package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.prod.FavouriteProducts;
import com.ktu.bitirmeproje.data.entity.prod.Product;

@Repository
public interface MyFavouriteProductsRepository extends PagingAndSortingRepository<FavouriteProducts, Long>{

	//@Query(value="SELECT Product FROM FavouriteProducts WHERE customerdetail_id=1",nativeQuery=true)
	@Query("SELECT f.product FROM FavouriteProducts f WHERE f.customer= :customer")
	public Page<Product> xyz(@Param("customer") CustomerDetails customer,Pageable pageable);
}
