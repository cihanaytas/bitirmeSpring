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

import com.ktu.bitirmeproje.data.entity.prod.FavouriteProducts;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails implements Serializable{

 
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "nickName")
	private UserAccount customer;
	private String detail;
	
    @OneToMany(targetEntity = Shopping.class, cascade = CascadeType.ALL)
    @JoinColumn(name="customerdetailId")
    private List<Shopping> shoppingList = new ArrayList<Shopping>();
    
    @OneToMany(targetEntity = FavouriteProducts.class, cascade = CascadeType.ALL)
    @JoinColumn(name="customerdetailId")
    private List<FavouriteProducts> favourites = new ArrayList<FavouriteProducts>();
	
}
