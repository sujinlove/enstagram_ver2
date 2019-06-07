package com.enstagram.mapper;

import java.util.List;
import java.util.Map;

//import org.apache.ibatis.annotations.Select;

import com.enstagram.model.EnstaAccount;
import com.enstagram.model.EnstaFeed;
import com.enstagram.model.EnstaFollow;

public interface EnstaMapper {

	/*
	 * Ensta Account
	 */

	public List<EnstaAccount> getAccountList() throws Exception;

	void createAccount(EnstaAccount enstaAccount);

	public Map<String, Object> getAccountInfo(int accnt_num);

	public Integer checkAccountId(String id);

	EnstaAccount getAccount(String id);

	public Integer getAccountNum(String id);

	void editAccountInfo(EnstaAccount enstaAccount);

	void editProfile(EnstaAccount enstaAccount);

	void followUser(EnstaFollow enstaFollow);

	void unfollowUser(EnstaFollow enstaFollow);

	public Integer[] getFollowingList(Integer accnt_num);

	public Integer[] getFollowerList(Integer following_num);

	/*
	 * Ensta Feed
	 */

	void createFeed(EnstaFeed enstaFeed);

	public Map<String, Object> getFeedInfo(Integer feed_num);

	public Integer[] getFollowFeed(Integer accnt_num);

	public String getFeedFileName(Integer feed_num);

	void editFeed(EnstaFeed enstaFeed);

	void removeFeed(Integer feed_num);

	void updateHeart(Integer feed_num);

	void likeFeed(EnstaFeed enstaFeed);

	void unlikeFeed(EnstaFeed enstaFeed);

	public Integer[] getFeedHeartList(Integer feed_num);

	public Integer[] getHeartList(Integer accnt_num);

	public String[] getFeedList(Integer feed_num);

	public Integer checkFeedNum(Integer feed_num);
	/*
	 * Recommend Friends
	 */

	public Integer[] getRecommendUser();

}
