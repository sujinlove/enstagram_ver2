package com.enstagram.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.enstagram.model.EnstaAccount;
import com.enstagram.service.EnstaService;

@RestController
public class EnstaAccountController {

	@Autowired EnstaService enstaService;

	/*
	 * Create Account
	 */
	
	@RequestMapping(value="/api/account", method = RequestMethod.POST)
	public void createAccount (@RequestBody EnstaAccount enstaAccount) {
		enstaService.createAccount(enstaAccount);
	}
	
	/*
	 * Login Account
	 */

	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView viewLogin(HttpServletRequest request) {
        String errorMessge = null;
        ModelAndView mav = new ModelAndView();
        String referer = request.getHeader("REFERER");
        if(referer != null) {
        	if (referer.equals(request.getRequestURL().toString())) {
                errorMessge = enstaService.getLoginError();
                mav.addObject("loginError", errorMessge);
        	}
        }
        return mav;
	}
}
