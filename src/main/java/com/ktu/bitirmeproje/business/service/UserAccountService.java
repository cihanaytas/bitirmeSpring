package com.ktu.bitirmeproje.business.service;

import com.ktu.bitirmeproje.business.dto.UserAccountDto;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;

public interface UserAccountService {

	public boolean login(ReqBodyLogin reqBody);
	public UserAccount save(UserAccountDto uaDto);
	public String getUserRole(String username);
}
