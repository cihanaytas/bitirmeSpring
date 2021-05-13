package com.ktu.bitirmeproje.data.entity.prod;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"category1", "category2","oran"})
	}) 
public class AprioriOran {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String category1;
	private String category2;
	private String oran;
}
