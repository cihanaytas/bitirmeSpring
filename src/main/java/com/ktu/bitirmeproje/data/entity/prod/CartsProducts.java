package com.ktu.bitirmeproje.data.entity.prod;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ktu.bitirmeproje.data.entity.CustomerDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsProducts {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shoppingId")
	private Shopping shop;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	private int quantity;
}
