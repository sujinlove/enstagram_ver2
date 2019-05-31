package com.enstagram.service;

import java.util.List;
import java.util.Map;

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
	
	public Integer getAccountNum(String id) {
		return enstaMapper.getAccountNum(id);
	}
	
	public Map<String, Object> getAccountInfo(int accnt_num) {
		return enstaMapper.getAccountInfo(accnt_num);
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
	 * Get Feed File Name
	 */
	
	public String getFeedFileName(Integer feed_num) {
		return enstaMapper.getFeedFileName(feed_num);
	}
	
	/*
	 * Upload Feed
	 */
	
	public void createFeed(EnstaFeed enstaFeed) {
		enstaMapper.createFeed(enstaFeed);
	}
	
	/*
	 * Get Feed
	 */
	
	public Map<String, Object> getFeed(Integer feed_num) {
		return enstaMapper.getFeed(feed_num);
	}

	/*
	 * Edit Feed
	 */
	
	public void editFeed(EnstaFeed enstaFeed) {
		enstaMapper.editFeed(enstaFeed);
	}
	/*
	 * Remove Feed
	 */
	
	public void removeFeed(Integer feed_num) {
		enstaMapper.removeFeed(feed_num);
	}
	
	/*
	 * Like Feed
	 */
	
	public void likeFeed(EnstaFeed enstaFeed) {
		enstaMapper.likeFeed(enstaFeed);
	}
	
	/*
	 * Unlike Feed
	 */
	
	public void unlikeFeed(EnstaFeed enstaFeed) {
		enstaMapper.unlikeFeed(enstaFeed);
	}
	
	/*
	 * Update heart
	 */
	
	public void updateHeart(Integer feed_num) {
		enstaMapper.updateHeart(feed_num);
	}
	
	/*
	 * Get Feed Heart List
	 */
	
	public List<EnstaAccount> getFeedHeartList(Integer feed_num) {
		return enstaMapper.getFeedHeartList(feed_num);
	}
	
	/*
	 * Get My Heart List
	 */
	
	public String[] getMyHeartList(Integer accnt_num) {
		return enstaMapper.getMyHeartList(accnt_num);
	}
	
	/*
	 * Get My Feed List
	 */
	
	public String[] getMyFeedList(Integer accnt_num) {
		return enstaMapper.getMyFeedList(accnt_num);
	}
	
}
