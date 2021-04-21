package com.ktu.bitirmeproje.business.dto.prod;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.ktu.bitirmeproje.utils.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable{
	

	private static final long serialVersionUID = 1L;

	public ProductDto(@NotNull long price, @NotNull String brand, @NotNull String model, @NotNull Date date,
			@NotNull String storeNickName, @NotNull CategoryType category, @NotNull String features,
			@NotNull int units) {
		this.price = price;
		this.brand = brand;
		this.model = model;
		this.date = date;
		this.storeNickName = storeNickName;
		this.category = category;
		this.features = features;
		this.units = units;
	}

	private long id;

	@NotNull
	private long price;
	
	@NotNull
	private String brand;

	@NotNull
	private String model;

	
	private Date date;
		
	private String storeNickName;
	
	@NotNull
	private CategoryType category;
	
	@NotNull
	private String features;
	
	@NotNull
	private int units;
	
	//private List<PointsOfProduct> points = new ArrayList<PointsOfProduct>();
	
	private List<Double> points;
	
	private List<String> images;
	
}
