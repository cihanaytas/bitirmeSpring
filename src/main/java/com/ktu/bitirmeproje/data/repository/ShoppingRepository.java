package com.ktu.bitirmeproje.data.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long>{

	List<Shopping> findByCustomerOrderByDateDesc(CustomerDetails customer);


}
