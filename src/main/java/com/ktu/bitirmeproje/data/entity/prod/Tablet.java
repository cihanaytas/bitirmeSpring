package com.ktu.bitirmeproje.data.entity.prod;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tablet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tabletId;
	
	@OneToOne
	@JoinColumn(name = "productID")
	private Product product;
	@Enumerated(EnumType.STRING)
	private PhoneType phonetype;
	private String brand;
	private String model;
	private int RAMsize;
	private int camMP;
	private long memory;
	
	@Enumerated(EnumType.STRING)
	private ColorType color;
 	
	
}
