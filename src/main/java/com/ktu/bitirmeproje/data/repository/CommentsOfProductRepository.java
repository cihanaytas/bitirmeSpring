package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.prod.CommentsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;


@Repository
public interface CommentsOfProductRepository extends CrudRepository<CommentsOfProduct, Long>{

	@Query("select c from CommentsOfProduct c where c.product= :product order by date desc")
	public List<CommentsOfProduct> getCommentList(@Param("product")Product product);
}
