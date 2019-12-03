package com.example.restservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservices.entities.User;
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
	
	
	
}
