package com.enstagram.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.enstagram.MyAuthenticaion;
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

    public EnstaAccount login(String id, String passwd) {
        EnstaAccount user = enstaMapper.getAccount(id);
        if(user == null) return null;
        if(user.getPasswd().equals(passwd) == false) return null;
        return user;
    }

    public static EnstaAccount getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticaion)
            return ((MyAuthenticaion) authentication).getEnstaAccount();
        return null;
    }

    public static void setCurrentUser(EnstaAccount enstaAccount) {
        ((MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setEnstaAccount(enstaAccount);
    }
}
