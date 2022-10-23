package com.example.apimongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apimongodb.domain.Post;
import com.example.apimongodb.repositories.PostRepository;
import com.example.apimongodb.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public Post findById(String id) {
		Optional <Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not founded: " + id));
	}
}