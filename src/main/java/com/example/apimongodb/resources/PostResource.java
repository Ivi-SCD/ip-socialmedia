package com.example.apimongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apimongodb.domain.Post;
import com.example.apimongodb.resources.util.URL;
import com.example.apimongodb.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	PostService postService;
	
	@RequestMapping
	public ResponseEntity <List <Post>> findAll() {
		List <Post> posts = postService.findAll();
		
		return ResponseEntity.ok().body(posts);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity <Post> findById(@PathVariable String id) {
		Post post = postService.findById(id);
		
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	public ResponseEntity <List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List <Post> post = postService.findByTitle(text);
		
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
	public ResponseEntity <List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue = "") String text,
			@RequestParam(value="minDate", defaultValue = "") String minDate,
			@RequestParam(value="maxDate", defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		
		List <Post> post = postService.fullSearch(text, min, max);
		
		return ResponseEntity.ok().body(post);
	}
}