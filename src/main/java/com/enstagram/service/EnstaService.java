package com.enstagram.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enstagram.mapper.EnstaMapper;
import com.enstagram.model.EnstaAccount;
import com.enstagram.model.EnstaFeed;

@Service
public class EnstaService {
	String loginError = null;
	
	@Autowired
	EnstaMapper enstaMapper;

	/*
	 * Get Account List
	 */

//	public List<EnstaAccount> accountList() throws Exception {
//		return enstaMapper.getAccountList();
//	}
	
	/*
	 * Get Account
	 */

	public void getAccount(String id) {
		enstaMapper.getAccount(id);
	}
	
	public List<EnstaAccount> getAccountInfo(String id) {
		return enstaMapper.getAccountInfo(id);
	}

	/*
	 * Create Account
	 */

	public void createAccount(EnstaAccount enstaAccount) {
		enstaMapper.createAccount(enstaAccount);
	}

	/*
	 * Login
	 */

	public EnstaAccount login(String id, String passwd) {
		EnstaAccount user = enstaMapper.getAccount(id);
		loginError = null;
		
		if (user == null) {
			loginError = "입력한 사용자 이름을 사용하는 계정을 찾을 수 없습니다. 사용자 이름을 확인하고 다시 시도하세요.";
		}
		else if (user.getPasswd().equals(passwd) == false) {
			loginError = "잘못된 비밀번호입니다. 다시 확인하세요.";
		}
		
		if(loginError == null) {
			return user;
		} else {
			return null;
		}
	}
	
	public String getLoginError () {
		return loginError;
	}
	
	/*
	 * Edit Profile
	 */
	
	public void editProfile(EnstaAccount enstaAccount) {
		enstaMapper.editProfile(enstaAccount);
	}
	
	/*
	 * Get Feed List
	 */
	
	public List<EnstaFeed> feedList() {
		return enstaMapper.getFeedList();
	}
	
	/*
	 * Upload feed
	 */
	
	public void createFeed(EnstaFeed enstaFeed) {
		enstaMapper.createFeed(enstaFeed);
	}
	
}
