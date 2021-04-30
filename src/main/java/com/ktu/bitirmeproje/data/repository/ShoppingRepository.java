package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.prod.Shopping;

@Repository
public interface ShoppingRepository extends CrudRepository<Shopping, Long>{

}
