package com.ktu.bitirmeproje.data.repository;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;

@Repository
public interface MyProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByPrice(double price, Pageable pageable);

    Page<Product> findAllByCategory(String category, Pageable pageable);
    
    Page<Product> findAllByStore(Pageable pageable, UserAccount store);


    
}