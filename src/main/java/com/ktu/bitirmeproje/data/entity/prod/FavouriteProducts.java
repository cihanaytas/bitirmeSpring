package com.ktu.bitirmeproje.data.entity.prod;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ktu.bitirmeproje.data.entity.CustomerDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"customerdetailId", "productID"})
	}) 
public class FavouriteProducts {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerdetailId")
	private CustomerDetails customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;
}