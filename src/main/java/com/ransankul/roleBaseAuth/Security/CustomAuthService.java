package com.ransankul.roleBaseAuth.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ransankul.roleBaseAuth.exception.ResourceNotFoundException;
import com.ransankul.roleBaseAuth.model.User;
import com.ransankul.roleBaseAuth.repositery.UserRepository;

@Service
public class CustomAuthService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Long id = Long.valueOf(username);
		User user = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id, "User Not Found"));
		return user;
	}

}
