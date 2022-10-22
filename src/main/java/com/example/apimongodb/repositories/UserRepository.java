package com.example.apimongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.apimongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository <User, String> {
	
}	