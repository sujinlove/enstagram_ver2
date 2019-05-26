package com.enstagram.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
	
	/*
	 * Account Info
	 */
	
	@RequestMapping(value = "/api/user", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> currentUserInfo(@ModelAttribute EnstaAccount enstaAccount){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = authentication.getName();
		enstaService.getAccount(currentUser);
		enstaAccount.setId(currentUser);
		
		Map<String, Object> map = enstaService.getAccountInfo(currentUser);
		map.put("heartList", enstaService.getHeartList(enstaService.getAccountNum(currentUser)));
		map.put("feedList", enstaService.getMyFeedList(enstaService.getAccountNum(currentUser)));
		
        return map;
    }
	
	/*
	 * Edit Profile
	 */

	@RequestMapping(value = "/api/profile", method = { RequestMethod.POST, RequestMethod.GET })
	public void editProfile(@ModelAttribute EnstaAccount enstaAccount, @RequestParam MultipartFile file) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = authentication.getName();
		String profileName = RandomStringUtils.randomAlphanumeric(10);
		File profilePath = new File("./src/main/resources/static/profile/" + currentUser);
		
		if(profilePath.exists() && profilePath.isDirectory()) {
			for (File f : profilePath.listFiles()) {
				if(f.delete()){
					profilePath.delete();
				}
			}
		}
		if(!profilePath.exists()) {
			profilePath.mkdirs();
		}
		
		enstaAccount.setId(currentUser);
		
		enstaAccount.setProfile("/profile/" + currentUser + "/" + profileName + "." + file.getOriginalFilename().split("\\.")[1]);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File("./src/main/resources/static/profile/" + currentUser + "/" + profileName + "." + file.getOriginalFilename().split("\\.")[1]));
			IOUtils.copy(file.getInputStream(), fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		enstaService.editProfile(enstaAccount);
	}
	

	
	/*
	 * Remove Profile
	 */

	@RequestMapping(value = "/api/profile/remove", method = { RequestMethod.POST, RequestMethod.GET })
	public void removeProfile(@ModelAttribute EnstaAccount enstaAccount, @RequestParam String filePath) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = authentication.getName();
		
		File profilePath = new File("./src/main/resources/static/profile/" + currentUser);
		
		if(profilePath.exists() && profilePath.isDirectory()) {
			for (File f : profilePath.listFiles()) {
				if(f.delete()){
					profilePath.delete();
				}
			}
		}
		
		enstaAccount.setProfile(filePath);
		enstaAccount.setId(authentication.getName());
		enstaService.editProfile(enstaAccount);
	}
}
