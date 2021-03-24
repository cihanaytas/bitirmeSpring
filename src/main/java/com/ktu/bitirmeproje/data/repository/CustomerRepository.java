package com.ktu.bitirmeproje.data.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDetails, Long>{
	
 
}



