package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.prod.CartsProducts;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;

@Repository
public interface CartsProductsRepository extends JpaRepository<CartsProducts, Long>{
	
	List<CartsProducts> findByShop(Shopping shop);
}
