package com.ktu.bitirmeproje.data.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.utils.CategoryType;

@Repository
public interface MyProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByPrice(double price, Pageable pageable);

    Page<Product> findAllByCategory(String category, Pageable pageable);
    
//   @Query("select * from Product where category=:TELEFON")
//   public List<Product> findAllProducts(Pageable pageable);
    
}