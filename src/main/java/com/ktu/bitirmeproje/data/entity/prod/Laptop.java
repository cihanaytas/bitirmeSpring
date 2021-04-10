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
import com.ktu.bitirmeproje.utils.ProcessorModelType;
import com.ktu.bitirmeproje.utils.ProcessorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laptop implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long laptopId;
	
	@OneToOne
	@JoinColumn(name = "productID")
	private Product product;

	private int RAMsize;
	private long HDDsize;
	@Enumerated(EnumType.STRING)
	private ProcessorType processorType;
	@Enumerated(EnumType.STRING)
	private ProcessorModelType processorModelType;
	@Enumerated(EnumType.STRING)
	private ColorType color;
	
		
	
}
