package com.example.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.restservices.entities.Order;
import com.example.restservices.entities.User;
import com.example.restservices.exceptions.UserNotFoundException;
import com.example.restservices.repositories.OrderRepository;
import com.example.restservices.repositories.UserRepository;



@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
//	get All Orders for a user
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException 
	{

		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		return userOptional.get().getOrders();
	}
	
	// Create Order

	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException 
	{
		Optional<User> userOptional = userRepository.findById(userid);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);

	}
	
	

	@GetMapping("/orders/{orderid}")
	public ResponseEntity<Optional<Order>> getOrderByOrderId(@PathVariable Long orderid)
	{
		Optional<Order> orderOptional = orderRepository.findById(orderid);
		if(!orderOptional.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order Not found");
	
		return new ResponseEntity<>(orderOptional,HttpStatus.OK);
	}
	
	

}
