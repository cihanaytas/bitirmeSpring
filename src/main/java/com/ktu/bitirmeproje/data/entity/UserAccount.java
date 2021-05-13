package com.ktu.bitirmeproje.data.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ktu.bitirmeproje.utils.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

	@Id
	private String nickName;
	private String name;
	private String surname;
	
	@Column(unique=true)
	private String e_mail;
	private String password;
	@Embedded
    private Address address;;
	@Enumerated(EnumType.STRING)
	private UserRole role;
}
