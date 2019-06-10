package com.enstagram.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enstagram.mapper.EnstaMapper;
import com.enstagram.model.EnstaAccount;
import com.enstagram.model.EnstaFeed;
import com.enstagram.model.EnstaFollow;
import com.enstagram.model.EnstaReply;

@Service
public class EnstaService {
	String loginError = null;

	@Autowired
	EnstaMapper enstaMapper;

	/*
	 * Check Account When Sign Up
	 */

	public Integer checkAccountId(String id) {
		return enstaMapper.checkAccountId(id);
	}

	/*
	 * Check Account When Login
	 */

	public void getAccount(String id) {
		enstaMapper.getAccount(id);
	}

	/*
	 * Change Id To Accnt_num
	 */

	public Integer getAccountNum(String id) {
		return enstaMapper.getAccountNum(id);
	}

	/*
	 * Get My Account Info
	 */

	public Map<String, Object> getMyAccountInfo(int accnt_num) {
		return enstaMapper.getMyAccountInfo(accnt_num);
	}

	/*
	 * Get Account Info
	 */

	public Map<String, Object> getAccountInfo(int accnt_num) {
		return enstaMapper.getAccountInfo(accnt_num);
	}

	/*
	 * Create Account When Sign Up
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
		} else if (user.getPasswd().equals(passwd) == false) {
			loginError = "잘못된 비밀번호입니다. 다시 확인하세요.";
		}

		if (loginError == null) {
			return user;
		} else {
			return null;
		}
	}

	public String getLoginError() {
		return loginError;
	}

	/*
	 * Edit Account Info
	 */

	public void editAccountInfo(EnstaAccount enstaAccount) {
		enstaMapper.editAccountInfo(enstaAccount);
	}

	/*
	 * Edit Profile
	 */

	public void editProfile(EnstaAccount enstaAccount) {
		enstaMapper.editProfile(enstaAccount);
	}

	/*
	 * Add Follow User
	 */

	public void followUser(EnstaFollow enstaFollow) {
		enstaMapper.followUser(enstaFollow);
	}

	/*
	 * Unfollow User
	 */

	public void unfollowUser(EnstaFollow enstaFollow) {
		enstaMapper.unfollowUser(enstaFollow);
	}

	/*
	 * Get Following List
	 */

	public Integer[] getFollowingList(Integer accnt_num) {
		return enstaMapper.getFollowingList(accnt_num);
	}

	/*
	 * Get Follower List
	 */

	public Integer[] getFollowerList(Integer following_num) {
		return enstaMapper.getFollowerList(following_num);
	}

	/*
	 * Get File Name of Feed
	 */

	public String getFeedFileName(Integer feed_num) {
		return enstaMapper.getFeedFileName(feed_num);
	}

	/*
	 * Create Feed When Upload File
	 */

	public void createFeed(EnstaFeed enstaFeed) {
		enstaMapper.createFeed(enstaFeed);
	}

	/*
	 * Get Feed Info
	 */

	public Map<String, Object> getFeedInfo(Integer feed_num) {
		return enstaMapper.getFeedInfo(feed_num);
	}

	/*
	 * Check Account When Sign Up
	 */

	public Integer checkFeedNum(Integer feed_num) {
		return enstaMapper.checkFeedNum(feed_num);
	}

	/*
	 * Get Feed List of Follow
	 */

	public Integer[] getFollowFeed(Integer accnt_num) {
		return enstaMapper.getFollowFeed(accnt_num);
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
	 * Add Heart to Feed
	 */

	public void likeFeed(EnstaFeed enstaFeed) {
		enstaMapper.likeFeed(enstaFeed);
	}

	/*
	 * Cancel Heart to Feed
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
	 * Get Heart List of Feed
	 */

	public Integer[] getFeedHeartList(Integer feed_num) {
		return enstaMapper.getFeedHeartList(feed_num);
	}

	/*
	 * Get Heart List
	 */

	public Integer[] getHeartList(Integer accnt_num) {
		return enstaMapper.getHeartList(accnt_num);
	}

	/*
	 * Get Feed List
	 */

	public String[] getFeedList(Integer accnt_num) {
		return enstaMapper.getFeedList(accnt_num);
	}

	/*
	 * Recommend Friends
	 */

	public Integer[] getRecommendUser() {
		return enstaMapper.getRecommendUser();
	}

	/*
	 * Create Reply
	 */

	public void createReply(EnstaReply enstaReply) {
		enstaMapper.createReply(enstaReply);
	}

	/*
	 * Get Reply Num
	 */

	public List<EnstaReply> getReplyList(Integer feed_num) {
		return enstaMapper.getReplyList(feed_num);
	}

	/*
	 * Remove Reply Info
	 */

	public void removeReplyInfo(Integer reply_num) {
		enstaMapper.removeReplyInfo(reply_num);
	}
}
