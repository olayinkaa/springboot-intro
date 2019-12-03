package com.example.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	public String HelloWorld()
	{
		return "Hello World mama";
	}
	
	@GetMapping("/hellobean")
	public UserDetails HelloWorldBean()
	{
		return new UserDetails("Ibrahim", "Olayinka", "Ilorin");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
