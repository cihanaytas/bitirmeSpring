package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;

@Repository
public interface PointsOfProductRepository extends CrudRepository<PointsOfProduct, Long>{

	@Query("select p from PointsOfProduct p where p.product= :product")
	public List<PointsOfProduct> getPointList(@Param("product")Product product);
	
	@Query("select count(*) from PointsOfProduct p where p.user= :user and p.product= :product")
	public int existByUser(@Param("user") UserAccount user,@Param("product") Product product);
	
	@Query("select p from PointsOfProduct p where p.user= :user and p.product= :product")
	public PointsOfProduct getPop(@Param("user") UserAccount user,@Param("product") Product product);
}
