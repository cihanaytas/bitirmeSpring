package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.prod.CartItemDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.dto.prod.ShoppingDto;
import com.ktu.bitirmeproje.business.service.ShoppingService;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.CartsProducts;
import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.ProductImages;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;
import com.ktu.bitirmeproje.data.repository.CartsProductsRepository;
import com.ktu.bitirmeproje.data.repository.CustomerRepository;
import com.ktu.bitirmeproje.data.repository.PointsOfProductRepository;
import com.ktu.bitirmeproje.data.repository.ProductImagesRepository;
import com.ktu.bitirmeproje.data.repository.ShoppingRepository;
import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
public class ShoppingServiseImpl implements ShoppingService{
	
	@Autowired
	private UserByAuth userByAuth;
	
	@Autowired
	private ShoppingRepository shopRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartsProductsRepository cpRep;
	
	@Autowired
	private PointsOfProductRepository popRep;
	
	@Autowired
	private ProductImagesRepository piRep;
	
	@Override
	public List<ShoppingDto> getShoppingList() {
		UserAccount user = userByAuth.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		
		List<Shopping> shopList = shopRepository.findByCustomerOrderByDateDesc(customer);
		List<ShoppingDto> shopDtoList = new ArrayList<ShoppingDto>();
		
		for(Shopping shop : shopList) {
			ShoppingDto shopDto = new ShoppingDto();
			convertToDto(shop,shopDto);
			shopDtoList.add(shopDto);
		}
		
		return shopDtoList;
	}
	
	
	@Override
	public List<CartItemDto> getCartItemList(long shoppingId) {
		Shopping shop = shopRepository.findById(shoppingId).orElse(null);

		List<CartsProducts> cartList = cpRep.findByShop(shop);
		List<CartItemDto> cartDtoList = new ArrayList<CartItemDto>();
		
		for(CartsProducts cart : cartList) {
			CartItemDto cartDto = new CartItemDto();
			convertToDto(cart, cartDto);
			cartDtoList.add(cartDto);
		}
		return cartDtoList;
	}
	
	
	
	public void convertToEntity(Shopping shop, ShoppingDto shopDto) {
		
	}
	
	
	
	private void convertToDto(Shopping shop, ShoppingDto shopDto) {
		shopDto.setId(shop.getID());
		shopDto.setCustomerNickName(shop.getCustomer().getCustomer().getNickName());
		shopDto.setDate(shop.getDate());
		shopDto.setTotalPrice(shop.getTotalPrice());
	
	}
	
	private void convertToDto(CartsProducts cart, CartItemDto cartDto) {
		ProductDto productDto = new ProductDto();
		convertToDto(cart.getProduct(),productDto);
		cartDto.setProduct(productDto);
		cartDto.setAdet(cart.getQuantity());
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
