package com.enstagram;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.enstagram.mapper.EnstaMapper;
import com.enstagram.model.EnstaAccount;
import com.enstagram.service.EnstaService;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider  {

	@Autowired
	EnstaMapper enstaMapper;
	
    @Autowired
    EnstaService enstaService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);
    }
	
    public Authentication authenticate(String id, String passwd) throws AuthenticationException {
    	EnstaAccount user = enstaService.login(id, passwd);
        if (user == null) return null;
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        String role = "";
        /**
         * 0 : 
         * 1 : 일반 사용
         * 2 : 
         * 3 : 
         * 4 : 관리자
         * */
        switch (user.getRole()) {
            case 0:
                role = "ROLE_NO";
                break;
            case 1:
                role = "ROLE_USER";
                break;
            case 2:
            case 3:
            case 4:
                role = "ROLE_ADMIN";
                break;
        }
        grantedAuthorityList.add(new SimpleGrantedAuthority(role));
        return new MyAuthenticaion(id, passwd, grantedAuthorityList, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}