package com.ktu.bitirmeproje.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetails implements Serializable{
	
 
	private static final long serialVersionUID = 1L;
 	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "nickName")
	private UserAccount store;

    @OneToMany(targetEntity = StorePoints.class, cascade = CascadeType.ALL)
    @JoinColumn(name="storedetailId")
    private List<StorePoints> points = new ArrayList<StorePoints>();
    
    @OneToMany(targetEntity = StoreComments.class, cascade = CascadeType.ALL) 
    @JoinColumn(name="storedetailId")
    private List<StoreComments> comments = new ArrayList<StoreComments>();
	
}
