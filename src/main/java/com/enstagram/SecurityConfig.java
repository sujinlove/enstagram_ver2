package com.enstagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.enstagram.service.EnstaService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired AuthProvider authProvider;
    
	@Autowired
	EnstaService enstaService;
	
	@Override public void configure(WebSecurity web) throws Exception { 
		web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico");	
	} 

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	
            .authorizeRequests()
                .antMatchers("/login","/account","/api/**","/service","/resources/**","/static/**").permitAll()
//                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/**").authenticated()
                .and()
                
           .formLogin()
           		.loginPage("/account")	
	            .usernameParameter("userId") 
	            .passwordParameter("userPasswd")
				.defaultSuccessUrl("/")
				.loginProcessingUrl("/api/login")
    			.failureUrl("/login")
                .permitAll()
                .and()
                
            .logout()
                .permitAll();
        
        	http.authenticationProvider(authProvider);
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
	}
	
}