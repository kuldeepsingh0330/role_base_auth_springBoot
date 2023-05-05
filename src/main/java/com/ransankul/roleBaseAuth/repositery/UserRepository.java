package com.ransankul.roleBaseAuth.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ransankul.roleBaseAuth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
