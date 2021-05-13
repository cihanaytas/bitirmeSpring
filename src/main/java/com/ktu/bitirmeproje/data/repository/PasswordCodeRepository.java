package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.PasswordCode;
import com.ktu.bitirmeproje.data.entity.UserAccount;

@Repository
public interface PasswordCodeRepository extends JpaRepository<PasswordCode, Long>{

	public PasswordCode findByUser(UserAccount user);
	
	public PasswordCode findByCode(String code);

}
