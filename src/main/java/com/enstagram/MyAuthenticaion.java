package com.enstagram;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.enstagram.model.EnstaAccount;

import lombok.Data;

@Data
public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
    private  static final long serialVersionUID = 1L;

    EnstaAccount enstaAccount;

    public MyAuthenticaion (String id, String passwd, List<GrantedAuthority> grantedAuthorityList, EnstaAccount enstaAccount) {
        super(id, passwd, grantedAuthorityList);
        this.enstaAccount = enstaAccount;
    }
}