package com.example.apimongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.apimongodb.domain.Post;
import com.example.apimongodb.domain.User;
import com.example.apimongodb.repositories.PostRepository;
import com.example.apimongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), 
				"Let's go to the trip!!", "I will trip to Sao Paulo. See you soon",
				maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"),
				"Good morning", "I wake up so excited today!", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}