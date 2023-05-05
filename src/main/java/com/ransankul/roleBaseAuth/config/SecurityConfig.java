package com.ransankul.roleBaseAuth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

import com.ransankul.roleBaseAuth.Security.CustomAuthService;
import com.ransankul.roleBaseAuth.exception.ResourceNotFoundException;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  implements AuthenticationProvider{
	
	@Autowired
	private CustomAuthService customAuthService;
    
	//Disable the SPRING default form base authentication AND make 3 "GET" API public
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
	    http.csrf().disable().authorizeHttpRequests()
	    .requestMatchers("/users/get_user/{id}").permitAll()
	    .requestMatchers("/users/get_all_user").permitAll()
	    .requestMatchers("/users/nearest_user/{n}").permitAll()
	    .anyRequest().authenticated()
	    .and().httpBasic();
	    return http.build();
	}


	// Authenticate the requested user
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final UserDetails userDetails = customAuthService.loadUserByUsername(username);
        if (userDetails.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
            throw new ResourceNotFoundException(Long.valueOf(username), "User not found");
        }
    }



    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    

}
