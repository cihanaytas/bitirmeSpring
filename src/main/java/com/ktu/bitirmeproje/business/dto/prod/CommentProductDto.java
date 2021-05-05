package com.ktu.bitirmeproje.business.dto.prod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentProductDto {

	private long id;
	private String username;
	private String comment;
	private long productId;
}
