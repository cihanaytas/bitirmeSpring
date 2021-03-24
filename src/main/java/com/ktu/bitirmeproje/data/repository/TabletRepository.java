package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.prod.Tablet;

@Repository
public interface TabletRepository extends CrudRepository<Tablet, Long>{

}
