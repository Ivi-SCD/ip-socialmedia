package com.example.apimongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apimongodb.domain.User;
import com.example.apimongodb.dto.UserDTO;
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
	
	public User insertUser(User user) {
		return userRepository.insert(user);
	}
	
	public void deleteUser(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User updateUser(User newUser) {
		Optional <User> oldUser = userRepository.findById(newUser.getId());
		updateUserData(oldUser.get(), newUser);
		return userRepository.save(oldUser.get());
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
	public void updateUserData(User oldUser, User newUser) {
		oldUser.setName(newUser.getName());
		oldUser.setEmail(newUser.getEmail());
	}
}