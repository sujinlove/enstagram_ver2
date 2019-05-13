package com.enstagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.ui.Model;

import com.enstagram.model.EnstaAccount;
import com.enstagram.service.EnstaService;

@RestController
@RequestMapping("/api")
public class EnstaController {

	@Autowired PasswordEncoder passwordEncoder;
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
//		enstaAccount.setRole(1);
		enstaAccount.setPasswd(passwordEncoder.encode(enstaAccount.getPasswd()));
		enstaService.create(enstaAccount);
	}
	
	/*
	 * Login
	 */
	
//	@RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET}) 
//	public String login(Model model, String error, String logout) { 
//		if ( error != null ) { 
//			model.addAttribute("errorMsg", "Your username and password are invalid."); 
//		} 
//		if ( logout != null ) { 
//			model.addAttribute("msg", "You have been logged out successfully"); 
//		} 
//		return "/login"; 
//	}
	
	@RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET})
	public void login(@RequestBody EnstaAccount enstaAccount) {
		System.out.println(enstaAccount.getId());
		System.out.println(enstaAccount.getName());
		System.out.println(enstaAccount.getNickname());
	}

}
