package com.ktu.bitirmeproje.data.entity.prod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shopping {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerdetailId")
	private CustomerDetails customer;
	
	private Date date;
	
	private double totalPrice;
	
	@OneToMany(targetEntity = CartsProducts.class, cascade = CascadeType.ALL)
	@JoinColumn(name="shoppingId")
	private List<CartsProducts> products = new ArrayList<CartsProducts>();
}








