package com.example.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user)
	{
		
		return userService.createUser(user);
	}
	
//getUserById
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id)
	{
		return userService.getUserById(id);
	}
	
	
	
}
