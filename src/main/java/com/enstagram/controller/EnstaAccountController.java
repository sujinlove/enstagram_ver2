package com.enstagram.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    
	/*
	 * Create Account
	 */

	@RequestMapping(value="/api/account", method = {RequestMethod.POST, RequestMethod.GET})
	public void createAccount (@RequestBody EnstaAccount enstaAccount) {
		enstaService.createAccount(enstaAccount);
	    autoLogin(enstaAccount.getId(), enstaAccount.getPasswd());
	}
 
    public void autoLogin(String id, String passwd) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
         = new UsernamePasswordAuthenticationToken(id, passwd, grantedAuthorityList); 

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
 
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
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
