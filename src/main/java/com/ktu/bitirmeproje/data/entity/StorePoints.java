package com.ktu.bitirmeproje.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"customername", "storedetailId"})
	}) 
public class StorePoints implements Serializable{
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customername")
	private UserAccount customer;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storedetailId")
	private StoreDetails store;
	
	private double point;

}
