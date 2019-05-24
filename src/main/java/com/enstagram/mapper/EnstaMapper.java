package com.enstagram.mapper;

import java.util.List;

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

	public List<EnstaAccount> getAccountInfo(String id);
	
	EnstaAccount getAccount(String id);
	
	EnstaAccount editProfile(EnstaAccount enstaAccount);

	/*
	 * Ensta Feed
	 */

	public List<EnstaFeed> getFeedList();

	void createFeed(EnstaFeed enstaFeed);

	public List<EnstaFeed> getFeed(String feed);
	
	void removeFeed(Integer feed_num);

}
