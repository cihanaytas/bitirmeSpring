package com.ktu.bitirmeproje.data.entity.prod;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.StoreDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationProduct {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shoppingId")
	private Shopping shop;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerdetailId")
	private CustomerDetails customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storedetailId")
	private StoreDetails store;
	
	@Lob
	private String bildirim;
	
	private Date date;
	
	private Boolean onay;
	
	private Boolean okundu;
	
	private int units;
	
	
}
