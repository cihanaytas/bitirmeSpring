package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationProductDto {

	
	private long ID;	

	private long shopId;

	private long productId;
	
	private long customerDetailId;
	
	private long storeDetailId;

	private String bildirim;
	
	private Date date;
	
	private Boolean onay;
	
	private Boolean okundu;
	
}
