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

	public Map<String, Object> getAccountInfo(String id);
	
	EnstaAccount getAccount(String id);
	
	public Integer getAccountNum (String id);
	
	EnstaAccount editProfile(EnstaAccount enstaAccount);

	/*
	 * Ensta Feed
	 */

	public List<EnstaFeed> getFeedList();

	void createFeed(EnstaFeed enstaFeed);

	public Map<String, Object> getFeed(Integer feed_num);
	
	void removeFeed(Integer feed_num);
	
	void updateHeart(Integer feed_num);
	
	void likeFeed(EnstaFeed enstaFeed);
	
	void unlikeFeed(EnstaFeed enstaFeed);

	public String[] getHeartList(Integer accnt_num);

}
