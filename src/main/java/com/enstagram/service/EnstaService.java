package com.enstagram.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enstagram.mapper.EnstaMapper;
import com.enstagram.model.EnstaAccount;

@Service
public class EnstaService {

	@Autowired
	EnstaMapper enstaMapper;

	public List<EnstaAccount> list() throws Exception {
		return enstaMapper.getList();
	}

	public void create(EnstaAccount enstaAccount) {
		enstaMapper.createAccount(enstaAccount);
	}
}
