package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.prod.CartsProducts;

@Repository
public interface CartsProductsRepository extends CrudRepository<CartsProducts, Long>{

}
