package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.ktu.bitirmeproje.data.entity.prod.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
