package com.ktu.bitirmeproje.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Long>{

	CustomerDetails findByCustomer(UserAccount user);


	
 
}



