package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.ProductImages;

@Repository
public interface ProductImagesRepository extends CrudRepository<ProductImages, Long>{

	@Query("select p from ProductImages p where p.product= :product")
	public List<ProductImages> getImageList(@Param("product")Product product);
	
}
