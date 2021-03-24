package com.ktu.bitirmeproje.data.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(RelationShipId.class)
public class PointAndComment{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pcID;
	
	
	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "nickName")
	private UserAccount customer;
	

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "storeNickName")
	private UserAccount store;
	
	String comment;
	int point;

	

}
