package com.example.restservices.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restservices.entities.User;
import com.example.restservices.exceptions.UserExistException;
import com.example.restservices.exceptions.UserNameNotFoundException;
import com.example.restservices.exceptions.UserNotFoundException;
import com.example.restservices.services.UserService;





@RestController
@Validated
public class UserController {

	
//	Auto wire the UserService
	
	
	@Autowired
	private UserService userService;
	
//	getAllUsers Method
	
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	
	
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder builder)
	{
		try
		{
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}
		catch(UserExistException ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
		
	}
	
	
	
	
//getUserById
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id)
	{
		try
		{
			return userService.getUserById(id);
		}
		catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
		
	}
	
	
	
//	updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user)
	{
		
		try
		{
			return userService.updateUserById(id,user);
		}
		catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
//  deleteUserById
	
	public void deleteUserById(@PathVariable("id") Long id)
	{
		userService.deleteUserById(id);
	}
	
	
	
// getUserByUsername
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException
	{
		User user = userService.getUserByUsername(username);
		
		if(user==null)
			throw new UserNameNotFoundException("'Username: '"+ username + "'not found in repository'");
		return user;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
