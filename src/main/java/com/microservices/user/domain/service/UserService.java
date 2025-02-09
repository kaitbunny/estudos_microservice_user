package com.microservices.user.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.user.domain.model.User;
import com.microservices.user.domain.repository.UserRepository;
import com.microservices.user.producer.UserProducer;

@Service
public class UserService {
	
	final UserRepository userRepository;
	final UserProducer userProducer;
	
	public UserService(UserRepository userRepository, UserProducer userProducer) {
		this.userRepository = userRepository;
		this.userProducer = userProducer;
	}
	
	@Transactional
	public User save(User user) {
		user = userRepository.save(user);
		userProducer.publishMessageEmail(user);
		return user;
	}
}
