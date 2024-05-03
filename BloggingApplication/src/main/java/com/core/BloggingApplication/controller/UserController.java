package com.core.BloggingApplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;//ResponseEntity

import com.core.BloggingApplication.payloads.ApiResponse;
import com.core.BloggingApplication.payloads.UserDTO;
import com.core.BloggingApplication.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class UserController{
@Autowired
private UserService userservice;

@PostMapping("/add")
public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto){//responseentity?read it ca..
	UserDTO Userdto=this.userservice.createUser(userdto);
	return new ResponseEntity<>(Userdto,HttpStatus.CREATED);
}	
@PutMapping("/update/{Id}")
public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userdto,@PathVariable("Id")  Integer Id){//responseentity?read it ca..
	UserDTO Userdto=this.userservice.updateUser(userdto,Id);
	return new ResponseEntity<>(Userdto,HttpStatus.OK);
}	
@GetMapping("/singleUser/{Id}")
public ResponseEntity<UserDTO>getUserById(@PathVariable("Id") Integer id){
	UserDTO userdto=this.userservice.getUserById(id);
	return new ResponseEntity<UserDTO>(userdto, HttpStatus.OK);
}
@DeleteMapping("/delete/{Id}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable("Id") Integer id){
	this.userservice.deleteById(id);
	System.out.print("User Deleted");
	return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
}

@GetMapping("/findAll")
public ResponseEntity<List<UserDTO>> getAll(){
	List<UserDTO> userdto=this.userservice.getAllUser();
	return  ResponseEntity.ok(userdto);
}	
}

