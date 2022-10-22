package com.example.apimongodb.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.apimongodb.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET)
	//Or @GetMapping
	public ResponseEntity<List<User>> findAll() {
		User maria = new User("1", "Maria Leonilda", "marialeonilda@gmail.com");
		User alex = new User("2", "Leandro Martins", "leandromartin@gmail.com");
		List<User> list = Arrays.asList(maria,alex);
		
		return ResponseEntity.ok().body(list);
	}
}