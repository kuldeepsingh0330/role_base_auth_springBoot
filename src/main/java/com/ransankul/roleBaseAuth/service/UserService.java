package com.ransankul.roleBaseAuth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ransankul.roleBaseAuth.model.User;

@Service
public interface UserService {

	public User addUser(User user);
	
	public void deleteUser(Long id);
	
	public User updateUser(Long id, User user);
	
	public User getUser(Long id);
	
	public List<User> getAllUsers();
	
	public List<User> getNearestUsers(int n);
}
