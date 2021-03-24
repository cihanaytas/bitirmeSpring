package com.ktu.bitirmeproje.data.entity.prod;

import java.io.Serializable;
 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.utils.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productID;
	
	private long price;
	
    @Temporal(TemporalType.TIMESTAMP)
	private java.util.Date date;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "nickName")
    private UserAccount store;
    
    @Enumerated(EnumType.STRING)
    private CategoryType category;
    
    @Lob
    private String features;
    
    private int units;

	
    
}
