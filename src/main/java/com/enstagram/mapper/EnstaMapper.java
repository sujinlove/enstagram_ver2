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

	EnstaAccount getEnstaAccount(EnstaAccount enstaAccount);

	EnstaAccount getAccount(String id);

	/*
	 * Ensta Feed
	 */

	public List<EnstaFeed> getFeedList();

	void createFeed(EnstaFeed enstaFeed);

}
