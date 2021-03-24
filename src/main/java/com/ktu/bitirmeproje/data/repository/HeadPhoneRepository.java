package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.prod.HeadPhone;

@Repository
public interface HeadPhoneRepository extends CrudRepository<HeadPhone, Long>{

}
