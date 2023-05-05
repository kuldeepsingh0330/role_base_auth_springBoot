package com.ransankul.roleBaseAuth.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ransankul.roleBaseAuth.config.Constant;
import com.ransankul.roleBaseAuth.model.Roles;
import com.ransankul.roleBaseAuth.model.User;
import com.ransankul.roleBaseAuth.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
    private UserService userService;
	
	
	//Controller to CREATE a user
    @PostMapping("/create_user")
    @PreAuthorize("hasRole('ADMIN')")
    public User addUser(@ModelAttribute User user) {
    	Set<Roles> role = new HashSet<>();
    	role.add(new Roles(Constant.READER_ID, Constant.READER_ROLE_VALUE));
    	user.setRoles(role);
        return userService.addUser(user);
    }

    //Controller to DELETE a user
    @DeleteMapping("/delete_user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    
    //Controller to UPDATE a user
    @PatchMapping("/update_user/")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@ModelAttribute User user) {
    	Long id = user.getId();
        return userService.updateUser(id, user);
    }

    //Controller to GET user BY ID
    @GetMapping("/get_user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //Controller to GET ALL USER
    @GetMapping("/get_all_user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //Controller to GET "n" Nearest users from coordinate (0,0)
    @GetMapping("/nearest_user/{n}")
    public List<User> getNearestUsers(@PathVariable int n) {
        return userService.getNearestUsers(n);
    }
}

