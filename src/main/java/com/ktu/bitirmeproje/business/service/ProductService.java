package com.ktu.bitirmeproje.business.service;

import java.util.List;

import com.ktu.bitirmeproje.business.dto.prod.CommentProductDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;


public interface ProductService {
	
	public List<ProductDto> getAllProducts();
	
	List<ProductDto> search(Integer pageNo, Integer pageSize, String sortBy, String category);
	
	public List<ProductDto> getAllProductByNickname(String nickName);
	
	public ProductDto getProduct(long productId);
	
	public List<ProductDto> getProductOran(long productId);
	
	public void pointProduct(long productId, double point);

	public void addProduct(ProductDto productDto);
	
	public void updateProduct(long productId,ProductDto productDto);
	
    public List<ProductDto> getAllProducts(Integer pageNo, Integer pageSize, String sortBy,Boolean orderby);
    
    public List<ProductDto> getAllProductByCategory(Integer pageNo, Integer pageSize, String sortBy,String category);
    
    public List<ProductDto> getAllProductByStore(Integer pageNo, Integer pageSize);

	public boolean addComment(CommentProductDto commentDto);

	public List<CommentProductDto> getCommentList(Long productId);

	public void deleteComment(Long commentId);
	
	public List<ProductDto> priceRange(Integer pageNo, Integer pageSize,Double min,Double max);

	List<ProductDto> getProductListByCategoryList(Integer pageNo, Integer pageSize, List<String> categories, Double min,Double max);






}
