package com.ktu.bitirmeproje.business.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.hibernate.mapping.Any;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.ProductImages;
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
	
	@Override
	public void addProduct(ProductDto productDto) {
		UserAccount user = uba.getUserByAuth();
		productDto.setStoreNickName(user.getNickName()); //ürünü ekleyen kullanıcı belirlendi 
		
		Date date= new Date();
		productDto.setDate(date); //tarih setlendi
		
		Product product = new Product();
		convertToEntity(product,productDto);
		
		
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
		PointsOfProduct pop = new PointsOfProduct();
		pop.setUser(user);
		pop.setProduct(product);
		pop.setPoint(point);
		product.getPoints().add(pop);

		productRepository.save(product);		
	}
	
	@Override
    public List<ProductDto> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
 
        Page<Product> pagedResult = proRep.findAll(paging);
        
         
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
	



	

	
	private void convertToDto(Product product, ProductDto productDto) {
		productDto.setId(product.getProductID());
		productDto.setPrice(product.getPrice());
		productDto.setBrand(product.getBrand());
		productDto.setModel(product.getModel());
		productDto.setDate(product.getDate());
		productDto.setStoreNickName(product.getStore().getNickName());
		productDto.setCategory(product.getCategory());
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
		product.setCategory(productDto.getCategory());
		product.setUnits(productDto.getUnits());
		product.setDate(productDto.getDate());
		

		for(String uri:productDto.getImages()) {
			ProductImages image = new ProductImages();
			image.setProduct(product);
			image.setImageUri(uri);
			product.getImages().add(image);
		}
		
		
		
		
	}














}