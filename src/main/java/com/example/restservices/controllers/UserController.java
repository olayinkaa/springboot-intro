package com.example.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservices.entities.User;
import com.example.restservices.services.UserService;





@RestController
public class UserController {

	
//	Autowire the UserService
	
	
	@Autowired
	private UserService userService;
	
//	getAllUsers Method
	
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	
	
	
	
	
	
}
