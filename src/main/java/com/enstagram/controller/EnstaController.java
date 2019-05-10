package com.enstagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enstagram.model.EnstaAccount;
import com.enstagram.service.EnstaService;

@RestController
@RequestMapping("/api")
public class EnstaController {
	
	@Autowired EnstaService enstaService;
	
	/*
	 * Get board_account Table DB
	 */

	@RequestMapping("/list")
    public List<EnstaAccount> list() throws Exception{
        return enstaService.list();
    }

	/*
	 * Create Account
	 */
	
	@RequestMapping(value="/account", method = {RequestMethod.POST, RequestMethod.GET})
	public void create(@RequestBody EnstaAccount enstaAccount) {
		enstaService.create(enstaAccount);
	}
}
