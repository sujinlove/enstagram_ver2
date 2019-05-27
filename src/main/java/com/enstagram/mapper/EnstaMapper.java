package com.enstagram.mapper;

import java.util.List;
import java.util.Map;

//import org.apache.ibatis.annotations.Select;

import com.enstagram.model.EnstaAccount;
import com.enstagram.model.EnstaFeed;

public interface EnstaMapper {

	/*
	 * Ensta Account
	 */

	public List<EnstaAccount> getAccountList() throws Exception;

	void createAccount(EnstaAccount enstaAccount);

//	EnstaAccount getEnstaAccount(EnstaAccount enstaAccount);

	public Map<String, Object> getAccountInfo(int accnt_num);
	
	EnstaAccount getAccount(String id);
	
	public Integer getAccountNum (String id);
	
	EnstaAccount editProfile(EnstaAccount enstaAccount);

	/*
	 * Ensta Feed
	 */

	void createFeed(EnstaFeed enstaFeed);

	public Map<String, Object> getFeed(Integer feed_num);
	
	public String getFeedFileName (Integer feed_num);
	
	void removeFeed(Integer feed_num);
	
	void updateHeart(Integer feed_num);
	
	void likeFeed(EnstaFeed enstaFeed);
	
	void unlikeFeed(EnstaFeed enstaFeed);

	List<EnstaAccount> getFeedHeartList(Integer feed_num);
	
	public String[] getMyHeartList(Integer accnt_num);

	public String[] getMyFeedList(Integer feed_num);

}
