package com.ktu.bitirmeproje.business.impl;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ktu.bitirmeproje.business.dto.FavouriteProductsDto;
import com.ktu.bitirmeproje.business.dto.prod.CartsProductsDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.impl.apriori.Apriori;
import com.ktu.bitirmeproje.business.service.CustomerService;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.CartsProducts;
import com.ktu.bitirmeproje.data.entity.prod.FavouriteProducts;
import com.ktu.bitirmeproje.data.entity.prod.NotificationProduct;
import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.ProductImages;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;
import com.ktu.bitirmeproje.data.repository.CustomerRepository;
import com.ktu.bitirmeproje.data.repository.FavouriteProductsRepository;
import com.ktu.bitirmeproje.data.repository.MyFavouriteProductsRepository;
import com.ktu.bitirmeproje.data.repository.MyProductRepository;
import com.ktu.bitirmeproje.data.repository.PointsOfProductRepository;
import com.ktu.bitirmeproje.data.repository.ProductImagesRepository;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.StoreRepository;
import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.UserByAuth;


//@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MyProductRepository proRep;
	
	@Autowired
	private UserByAuth authService;
	
	@Autowired
	private Apriori apriori;
	
	@Autowired
	private FavouriteProductsRepository favRep;
	
	@Autowired
	private PointsOfProductRepository popRep;
	
	@Autowired
	private ProductImagesRepository piRep;
 
	
	@Override
	public void sales(List<CartsProductsDto> cartListDto) throws IOException {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		
		//shopping
		Shopping shopping = new Shopping();
		Date date = new Date();
		shopping.setDate(date);
		shopping.setCustomer(customer);
		
		double totalPrice = 0;
					
		List<CartsProducts> cartList = new ArrayList<CartsProducts>();
		for(CartsProductsDto cartDto : cartListDto) {
			CartsProducts cart = new CartsProducts();
			totalPrice+= cartDto.getPrice() * cartDto.getQuantity();
			converToEntityCartProducts(cart, cartDto);
			cartList.add(cart);
		}
		
		shopping.setTotalPrice(totalPrice);

		
		for(CartsProducts cart : cartList) {
			shopping.getProducts().add(cart);
		}
		
		
		customer.getShoppingList().add(shopping);
		
		//addToFile(cartListDto);
			
	//	apriori.fun();
				
		customerRepository.save(customer);
		
		addNotification(cartListDto,shopping,customer);
		
	}
	
	
	@Async
	private void addNotification(List<CartsProductsDto> cartListDto, Shopping shopping, CustomerDetails customer) {
// System.out.println(shopping.getID());
		for(CartsProductsDto cpDto : cartListDto) {
			NotificationProduct np = new NotificationProduct();
			Optional<Product> product = productRepository.findById(cpDto.getProductId());
			UserAccount s = productRepository.getStoreName(cpDto.getProductId());
			StoreDetails store = storeRepository.getStoreDetail(s);
			String bildirim = customer.getCustomer().getName() + " " + customer.getCustomer().getSurname() + " ürünü satın aldı.";
			
			np.setProduct(product.get());
			np.setCustomer(customer);
			np.setStore(store);
			np.setDate(new Date());
			np.setOkundu(false);
			np.setBildirim(bildirim);
			//np.setOnay(false);
			
			//np.setShop(shopping);
		
			customer.getNotification().add(np);
			customerRepository.save(customer);
		}
		
	}


	@Override
	public List<Long> getFavouriteList() {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		List<Long> favDtoList = new ArrayList<Long>();
		
		for(FavouriteProducts fav : customer.getFavourites()) {
			FavouriteProductsDto favDto = new FavouriteProductsDto();
			convertToDto(fav,favDto);
			favDtoList.add(favDto.getProductId());
		}
		
		return favDtoList;
	}
	
	
	@Override
	public void addFavourite(Long productId) {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		Optional<Product> p = productRepository.findById(productId);
		FavouriteProducts fav = new FavouriteProducts();
		fav.setCustomer(customer);
		fav.setProduct(p.get());
		Date date = new Date();
		fav.setDate(date);
		customer.getFavourites().add(fav);
		
		customerRepository.save(customer);
	}
	
	@Override
	@Transactional
	public void deleteFavourite(Long productId) {
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		Optional<Product> p = productRepository.findById(productId);
		FavouriteProducts fav = favRep.getFav(p.get(), customer);
		favRep.deleteById(fav.getID());

	}
	
	@Autowired
	private MyFavouriteProductsRepository xrep;
	
	//returned favourite product list
	@Override
	public List<ProductDto> getFavouriteProductsList(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		
		UserAccount user = authService.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		
		
		
		List<Product> productList = new ArrayList<Product>();
		
		Page<Product> pagedResult = xrep.xyz(customer, paging);
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
		
//		for(FavouriteProducts fav : customer.getFavourites()) {
//			productList.add(fav.getProduct());
//		}
//		
//		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
//		
//		for(Product product : productList) {
//			ProductDto productDto = new ProductDto();
//			convertToDto(product, productDto);
//			productDtoList.add(productDto);
//		}
//				
//		return productDtoList;
	}

	
	
	
	private void converToEntityCartProducts(CartsProducts cart, CartsProductsDto cartDto) {
		cart.setQuantity(cartDto.getQuantity());
		Optional<Product> product = productRepository.findById(cartDto.getProductId());
		cart.setProduct(product.get());
		
	}
	
	private void addToFile(List<CartsProductsDto> cartListDto) {
        try(FileWriter fw = new FileWriter("C:\\Users\\cihanaytas\\Desktop\\ccc.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {String line="";
            List<String> categories = new ArrayList<String>();
        	for(int i=0; i<cartListDto.size(); i++) {
        		if(categories.contains(cartListDto.get(i).getCategory())) {
        			
        		}else {
        			categories.add(cartListDto.get(i).getCategory());
                	line+=cartListDto.get(i).getCategory();}
                if(i!=cartListDto.size()-1) {
                	line+=",";
                }
                }
        	out.println(line);
            }

        	catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
	}
	
	private void convertToDto(FavouriteProducts fav, FavouriteProductsDto favDto) {
		favDto.setCustomerNickName(fav.getCustomer().getCustomer().getNickName());
		favDto.setID(fav.getID());
		favDto.setDate(fav.getDate());
		favDto.setProductId(fav.getProduct().getProductID());
		
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













 

	
	
	
	



}
