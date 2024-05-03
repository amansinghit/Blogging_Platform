package com.core.BloggingApplication.service;

import java.util.List;

import com.core.BloggingApplication.payloads.UserDTO;

public interface UserService {

	public UserDTO createUser(UserDTO user);
	public UserDTO updateUser(UserDTO user,Integer id);
	public UserDTO getUserById(Integer id);
	public List<UserDTO> getAllUser();
	public void deleteById(Integer id);
	
	
	
	
	
}
