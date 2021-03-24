package com.ktu.bitirmeproje.business.dto;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class PointAndCommentDto {
	
	@NotNull
	private String nickName;
	@NotNull
	private String storeName;
	@NotNull
	@Size(min = 5 , max = 100 , message = "Your comment should be at least 5-100 character")
	private String comment;
	@NotNull
	@Size(min = 1 , max = 5 , message = "Your comment should be at least 1-5 point")
	private int point;
	
	
	public PointAndCommentDto() {
	}
	
	public PointAndCommentDto(String nickName, String storeName, String comment, int point) {
		this.nickName = nickName;
		this.storeName = storeName;
		this.comment = comment;
		this.point = point;
	}
	

	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

}
