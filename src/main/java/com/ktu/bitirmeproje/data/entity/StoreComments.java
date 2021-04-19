package com.ktu.bitirmeproje.data.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreComments {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customername")
	private UserAccount customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storedetailId")
	private StoreDetails store;
	
	@Lob
	private String comment;
}
