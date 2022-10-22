package com.example.apimongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apimongodb.domain.User;
import com.example.apimongodb.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List <User> findAll() {
		return userRepository.findAll();
	}
}