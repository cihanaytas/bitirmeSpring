package com.ktu.bitirmeproje.data.entity.prod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Index;
import javax.persistence.TemporalType;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Document(indexName = "product")

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name="product",indexes = @Index(name="idx",columnList = "brand,model,category"))
public class Product implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productID;
	
	private long price;
	
	private String brand;
	
	private String model;
	
    @Temporal(TemporalType.TIMESTAMP)
	private java.util.Date date;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "nickName")
    private UserAccount store;
    
    private String category;
    
    @Lob
    private String features;
    
    private int units;
    
    @OneToMany(targetEntity = PointsOfProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name="productId")
    private List<PointsOfProduct> points = new ArrayList<PointsOfProduct>();
    
    @OneToMany(targetEntity = ProductImages.class, cascade = CascadeType.ALL)
    @JoinColumn(name= "productId")
    private List<ProductImages> images = new ArrayList<ProductImages>();
    
    @OneToMany(targetEntity = CommentsOfProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name= "productId")
    private List<CommentsOfProduct> comments = new ArrayList<CommentsOfProduct>();
    

	
    
}
