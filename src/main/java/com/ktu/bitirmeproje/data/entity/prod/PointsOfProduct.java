package com.ktu.bitirmeproje.data.entity.prod;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"username", "productID"})
	}) 
public class PointsOfProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	private UserAccount user;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "productID")
//	private Product product;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	private double point;
	

}
