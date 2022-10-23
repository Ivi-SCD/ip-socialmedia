package com.example.apimongodb.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity <List<UserDTO>> findAll() {
		List<User> allUsers = userService.findAll();
		List<UserDTO> allUsersDto = allUsers.stream().map(x -> new UserDTO(x)).toList();
		
		return ResponseEntity.ok().body(allUsersDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity <UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity <Void> insertUser(@RequestBody UserDTO userDto) {
		User user = userService.fromDTO(userDto);
		user = userService.insertUser(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity <Void> deleteById(@PathVariable String id) {
		userService.deleteUser(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity <Void> insertUser(@RequestBody UserDTO userDto, @PathVariable String id) {
		User user = userService.fromDTO(userDto);
		user.setId(id);
		user = userService.updateUser(user);
	
		return ResponseEntity.noContent().build();
	}
}