package com.ktu.bitirmeproje.data.repository;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;

@Repository
public interface MyProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByPrice(double price, Pageable pageable);

    Page<Product> findAllByCategory(String category, Pageable pageable);
    
    Page<Product> findAllByStore(Pageable pageable, UserAccount store);
    
    @Query(value="select * from Product where category like %:c%  or brand like %:c% or model like %:c%" ,nativeQuery= true)
    Page<Product> search(String c, Pageable pageable);
    
    Page<Product> findByPriceBetween(long min, long max, Pageable pageable);

	@Query(value="select * from Product where category in :categories",nativeQuery= true)
	public Page<Product> getProductListByCategoryList(@Param("categories") List<String> categories,Pageable pageable);
	
    
}