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
			loginError = "id error";
		}
		else if (user.getPasswd().equals(passwd) == false) {
			loginError = "password error";
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
	 * Get current user
	 */

//	public static EnstaAccount getCurrentUser() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication instanceof MyAuthenticaion)
//			return ((MyAuthenticaion) authentication).getEnstaAccount();
//		return null;
//	}

	/*
	 * Set current user
	 */

//	public static void setCurrentUser(EnstaAccount enstaAccount) {
//		((MyAuthenticaion) SecurityContextHolder.getContext().getAuthentication()).setEnstaAccount(enstaAccount);
//	}
	
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
