package com.example.apimongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.apimongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
}