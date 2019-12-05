package com.example.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.restservices.entities.User;
import com.example.restservices.exceptions.UserExistException;
import com.example.restservices.exceptions.UserNotFoundException;
import com.example.restservices.repositories.UserRepository;


@Service
public class UserService {

	
//	Auto wire the UserRepository
	
	@Autowired
	private UserRepository userRepository;

	
//	getAllUsers Method
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
//	createUser method
	public User createUser(User user) throws UserExistException
	{
		
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if(existingUser != null)
		{
			throw new UserExistException("User already exists in repository");
		}
		
		return userRepository.save(user);
		
		
	
	}
	
	
	
//	getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException
	{
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User Not found in user Repository");
		}
		return user;
	}

	
//	updateUserById
	
	public User updateUserById(Long id, User user) throws UserNotFoundException
	{
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("User Not found in user Repository, provide the correct user");
		}
		
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	
// deleteUserById
	
	public void deleteUserById(Long id)
	{
		
		if(userRepository.findById(id).isPresent())
		{
			userRepository.deleteById(id);
		}
		
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository");
		}
	}
	
	
	
// getUserByUsername 
	
	public User getUserByUsername(String username)
	{
		
		return userRepository.findByUsername(username);
	}
	
	
	
	
	
	
	
}
