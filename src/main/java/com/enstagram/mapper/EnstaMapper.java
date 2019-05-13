package com.enstagram.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.Select;

import com.enstagram.model.EnstaAccount;

public interface EnstaMapper {

	public List<EnstaAccount> getList() throws Exception;
	void createAccount(EnstaAccount enstaAccount);

	EnstaAccount getEnstaAccount(EnstaAccount enstaAccount);

	EnstaAccount getAccount(String id);
}
