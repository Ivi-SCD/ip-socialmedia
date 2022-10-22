package com.example.apimongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apimongodb.domain.User;
import com.example.apimongodb.repositories.UserRepository;
import com.example.apimongodb.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List <User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional <User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not founded: " + id));
	}
}