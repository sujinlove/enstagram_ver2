package com.enstagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.ui.Model;

import com.enstagram.model.EnstaAccount;
import com.enstagram.service.EnstaService;

@RestController
@RequestMapping("/api")
public class EnstaAccountController {

	@Autowired EnstaService enstaService;
	 
	/*
	 * Get board_account Table DB
	 */

//	@RequestMapping("/list")
//    public List<EnstaAccount> accountList() throws Exception{
//        return enstaService.accountList();
//    }

	/*
	 * Create Account
	 */
	
	@RequestMapping(value="/account", method = RequestMethod.POST)
	public void createAccount (@RequestBody EnstaAccount enstaAccount) {
		enstaService.createAccount(enstaAccount);
	}
	
	/*
	 * Login
	 */
	
//	@RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET}) 
//	public void login(Model model, String error, String logout) { 
//		model.addAttribute("msg", "test");
//		if ( error != null ) { 
//			model.addAttribute("msg", "Your username and password are invalid."); 
//		} 
//		if ( logout != null ) { 
//			model.addAttribute("msg", "You have been logged out successfully"); 
//		}
//	}

}
