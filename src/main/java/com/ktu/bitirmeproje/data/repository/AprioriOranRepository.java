package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.prod.AprioriOran;

@Repository
public interface AprioriOranRepository extends CrudRepository<AprioriOran, Long>{
	
	@Query("select count(*) from AprioriOran a where a.category1= :category1 and a.category2 = :category2")
	public int exist(@Param("category1") String category1, @Param("category2") String category2);
	
	@Query("select a from AprioriOran a where a.category1= :category1 and a.category2 = :category2")
	public AprioriOran getExist(@Param("category1") String category1, @Param("category2") String category2);

	@Query("select a from AprioriOran a where a.category1= :category1 and a.category2 = :category2 and a.oran= :oran")
	public AprioriOran getExist2(@Param("category1") String category1, @Param("category2") String category2, @Param("oran") String oran);

 


}
