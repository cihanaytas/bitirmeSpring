package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.UserAccount;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, String>{
	
	@Query("select u.role from UserAccount u where u.nickName = :nickName")
	public String getUserRole(@Param("nickName") String nickName);
 
}
