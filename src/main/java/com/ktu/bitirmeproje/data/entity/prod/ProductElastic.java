package com.ktu.bitirmeproje.data.entity.prod;

import java.util.List;

import javax.persistence.Entity;
 
import javax.persistence.Lob;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.ktu.bitirmeproje.data.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class ProductElastic {
	
	@Id
	private long productID;
	
	private long price;
	
	private String brand;
	
	private String model;
	private java.util.Date date;
    private String category;
    private String features;
    private int units;

}
