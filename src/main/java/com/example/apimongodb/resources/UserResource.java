package com.example.apimongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.apimongodb.domain.User;
import com.example.apimongodb.dto.UserDTO;
import com.example.apimongodb.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	//Or @GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> allUsers = userService.findAll();
		List<UserDTO> allUsersDto = allUsers.stream().map(x -> new UserDTO(x)).toList();
		
		return ResponseEntity.ok().body(allUsersDto);
	}
}