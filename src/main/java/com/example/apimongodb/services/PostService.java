package com.example.apimongodb.services;

import java.util.Date;
import java.util.List;
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
	
	public List <Post> findAll() {
		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		Optional <Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not founded: " + id));
	}
	
	public List <Post> findByTitle(String text) {
		return postRepository.findByTitle(text);
	}
	
	public List <Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60* 1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}