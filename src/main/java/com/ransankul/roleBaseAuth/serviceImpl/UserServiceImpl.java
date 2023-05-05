package com.ransankul.roleBaseAuth.serviceImpl;

import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ransankul.roleBaseAuth.exception.ResourceNotFoundException;
import com.ransankul.roleBaseAuth.model.User;
import com.ransankul.roleBaseAuth.repositery.UserRepository;
import com.ransankul.roleBaseAuth.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id,"User Not Found"));
        existingUser.setName(user.getName());
        existingUser.setLatitude(user.getLatitude());
        existingUser.setLongitude(user.getLongitude());
        return userRepository.save(existingUser);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id,"User Not Found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getNearestUsers(int n) {
        List<User> allUsers = userRepository.findAll();
        allUsers.sort(Comparator.comparingDouble(u -> u.getLatitude()+u.getLongitude()));
        return allUsers.size() < n?allUsers : allUsers.subList(0, n);
    }
    
}
