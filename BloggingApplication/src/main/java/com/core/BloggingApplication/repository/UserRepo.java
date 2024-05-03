package com.core.BloggingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.BloggingApplication.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{

	//JpaRepository<User,Integer> all methods of this class inherited into UserRepo
	//we can take the reference this class and use those mthods
}
