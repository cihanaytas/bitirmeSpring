package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.prod.CommentProductDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.CommentsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.ProductImages;
import com.ktu.bitirmeproje.data.repository.CommentsOfProductRepository;
import com.ktu.bitirmeproje.data.repository.MyProductRepository;
import com.ktu.bitirmeproje.data.repository.PointsOfProductRepository;
import com.ktu.bitirmeproje.data.repository.ProductImagesRepository;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserAccountRepository uaRep;
	
	@Autowired
	private UserByAuth uba;

	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private PointsOfProductRepository popRep;
	
	@Autowired
	private ProductImagesRepository piRep;
	
	@Autowired
	private MyProductRepository proRep;
	
	@Autowired
	private CommentsOfProductRepository copRep;
	
	@Override
	public void addProduct(ProductDto productDto) {
		UserAccount user = uba.getUserByAuth();
		productDto.setStoreNickName(user.getNickName()); //ürünü ekleyen kullanıcı belirlendi 
		
		if(productRepository.isExist(user, productDto.getBrand(), productDto.getModel())!=0) {
			System.out.println("eklenmedi");
		}
		
		else {
			Date date= new Date();
			productDto.setDate(date); //tarih setlendi
			
			Product product = new Product();
			convertToEntity(product,productDto);
			
			productRepository.save(product);
		}
				
	}
	

	@Override
	public void updateProduct(long productId,ProductDto productDto) {
		UserAccount user = uba.getUserByAuth();
		productDto.setStoreNickName(user.getNickName());
		Optional<Product> p = productRepository.findById(productId);
		Product product = p.get();
		List<String> imageListString = new ArrayList<String>();
		for(ProductImages image : product.getImages()) {
			imageListString.add(image.getImageUri());
		}
		List<String> union = new ArrayList<String>(imageListString);
		union.addAll(productDto.getImages());
		System.out.println(union);
		List<String> intersection = new ArrayList<String>(imageListString);
		intersection.retainAll(productDto.getImages());
		union.removeAll(intersection);
		
		productDto.setImages(union);

		
		Date date= new Date();
		productDto.setDate(date);
		convertToEntity(product, productDto);
		productRepository.save(product);
		
	}


	

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> list = (List<Product>) productRepository.getAllProduct();
		List<ProductDto> listDto = new ArrayList<>();	
		
		for(Product product : list) {
			ProductDto productDto = new ProductDto();
			//ModelMapper modelMapper = new ModelMapper();
	 		//productDto = modelMapper.map(product, ProductDto.class);
			convertToDto(product, productDto);
			listDto.add(productDto);
		}

		return listDto;
	}	
	
	@Override
	public List<ProductDto> getAllProductByNickname(String nickName) {
		Optional<UserAccount> store = uaRep.findById(nickName);
		
		List<Product> list = (List<Product>) productRepository.getAllProductByNickname(store.get());
		List<ProductDto> listDto = new ArrayList<>();	
		
		for(Product product : list) {
			ProductDto productDto = new ProductDto();
			//ModelMapper modelMapper = new ModelMapper();
	 		//productDto = modelMapper.map(product, ProductDto.class);
			convertToDto(product, productDto);
			listDto.add(productDto);
		}

		return listDto;
	}
	

	@Override
	public ProductDto getProduct(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		ProductDto productDto = new ProductDto();
		//ModelMapper modelMapper = new ModelMapper();
 		//productDto = modelMapper.map(product.get(), ProductDto.class);
		convertToDto(product.get(), productDto);
		return productDto;
	}
	
	
	@Override
	public void pointProduct(long productId, double point) {
		Optional<Product> p = productRepository.findById(productId);
		Product product = p.get();
		UserAccount user = uba.getUserByAuth();
		if(popRep.existByUser(user)==0) {
			PointsOfProduct pop = new PointsOfProduct();
			pop.setUser(user);
			pop.setProduct(product);
			pop.setPoint(point);
			product.getPoints().add(pop);
		}
		else {
			PointsOfProduct pop = popRep.getPop(user, product);
			pop.setPoint(point);
		}
		
	
		productRepository.save(product);		
	}
	
	@Override
    public List<ProductDto> getAllProducts(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
 
        Page<Product> pagedResult = proRep.findAll(paging);

        return returnProduct(pagedResult);
    }
	
	@Override
	public List<ProductDto> getAllProductByCategory(Integer pageNo, Integer pageSize, String sortBy,String category) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Product> pagedResult = proRep.findAllByCategory(category, paging);
      
        return returnProduct(pagedResult);
	}
	
	public List<ProductDto> returnProduct(Page<Product> pagedResult){
        if(pagedResult.hasContent()) {
        	List<Product> list = pagedResult.getContent();
    		List<ProductDto> listDto = new ArrayList<>();	
    		for(Product product : list) {
    			ProductDto productDto = new ProductDto();
    			convertToDto(product, productDto);
    			listDto.add(productDto);
    		}
            return listDto;
        } else {
            return new ArrayList<ProductDto>();
        }
	}
	
	
	@Override
	public List<ProductDto> getAllProductByStore(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("date").descending());
		UserAccount store = uba.getUserByAuth();
	    Page<Product> pagedResult = proRep.findAllByStore(paging, store);
	    
	    return returnProduct(pagedResult);
	}
	
	
	@Override
	@Transactional
	public boolean addComment(CommentProductDto commentDto) {
		CommentsOfProduct comment0 = copRep.findById(commentDto.getId()).orElse(null);
		
		if(comment0!=null) {
			Date date = new Date();
			comment0.setDate(date);
			comment0.setComment(commentDto.getComment());
			return true;
			
		}
		else {
		UserAccount user = uba.getUserByAuth();
		Optional<Product> p = productRepository.findById(commentDto.getProductId());
		Product product = p.get();
		CommentsOfProduct comment = new CommentsOfProduct();
		convertToEntity(comment, commentDto);
		comment.setUser(user);
		Date date = new Date();
		comment.setDate(date);
 
		product.getComments().add(comment);
		
		if(productRepository.save(product) != null)
			return true;
		else
			return false;
		}
		

		
	}
	
	
	@Override
	public List<CommentProductDto> getCommentList(Long productId) {
		Product product = productRepository.findById(productId).orElse(null);;
		List<CommentProductDto> commentDtoList = new ArrayList<CommentProductDto>();
		List<CommentsOfProduct> commentList = copRep.getCommentList(product);
		
		if(commentList.isEmpty()) {}
		
		else {
			UserAccount user = uba.getUserByAuth();
		for(CommentsOfProduct comment : commentList) {
			CommentProductDto commentDto = new CommentProductDto();
			convertToDto(comment, commentDto, user);
			commentDtoList.add(commentDto);
		}
		}

		return commentDtoList;
	}
	
	
	@Override
	@Transactional
	public void deleteComment(Long commentId) {
		copRep.deleteById(commentId);

	}



	

	
	private void convertToDto(Product product, ProductDto productDto) {
		productDto.setId(product.getProductID());
		productDto.setPrice(product.getPrice());
		productDto.setBrand(product.getBrand());
		productDto.setModel(product.getModel());
		productDto.setDate(product.getDate());
		productDto.setStoreNickName(product.getStore().getNickName());
		productDto.setCategory(CategoryType.valueOf(product.getCategory()));
		productDto.setFeatures(product.getFeatures());
		productDto.setUnits(product.getUnits());

		Iterable<PointsOfProduct> pointList = popRep.getPointList(product);
		List<Double> plist = new ArrayList<Double>();
		for(PointsOfProduct p : pointList) {
			plist.add(p.getPoint());
		}	
		productDto.setPoints(plist);
		
		Iterable<ProductImages> imageList = piRep.getImageList(product);
		List<String> ilist = new ArrayList<String>();
		for(ProductImages i : imageList) {
			ilist.add(i.getImageUri());
		}	
		productDto.setImages(ilist);
		
		
 	}

	
	private void convertToEntity(Product product, ProductDto productDto) { 
		product.setPrice(productDto.getPrice());
		product.setBrand(productDto.getBrand());
		product.setModel(productDto.getModel());
		Optional<UserAccount> store = uaRepository.findById(productDto.getStoreNickName());
		product.setStore(store.get());	
		product.setFeatures(productDto.getFeatures());
		product.setCategory(productDto.getCategory().toString());
		product.setUnits(productDto.getUnits());
		product.setDate(productDto.getDate());

		for(String uri:productDto.getImages()) {
			ProductImages image = new ProductImages();
			image.setProduct(product);
			image.setImageUri(uri);
			product.getImages().add(image);
		}

	}
	
	
	private void convertToEntity(CommentsOfProduct comment, CommentProductDto commentDto) {
		comment.setComment(commentDto.getComment());
		Optional<Product> product = productRepository.findById(commentDto.getProductId());
		comment.setProduct(product.get());
	}
	
	
	private void convertToDto(CommentsOfProduct comment, CommentProductDto commentDto,UserAccount user) {
		commentDto.setId(comment.getID());
		commentDto.setComment(comment.getComment());
		commentDto.setUsername(comment.getUser().getNickName());
		commentDto.setProductId(comment.getProduct().getProductID());
		commentDto.setDate(comment.getDate());
		if(comment.getUser()==user) {
			commentDto.setByYou(true);
		}else {
			commentDto.setByYou(false);
		}
	}





























}