package com.example.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restservices.entities.User;



//Repository


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	

}
