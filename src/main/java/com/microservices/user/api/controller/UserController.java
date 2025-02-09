package com.microservices.user.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.domain.dto.UserRecordDTO;
import com.microservices.user.domain.model.User;
import com.microservices.user.domain.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user")
public class UserController {
	
	final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDTO userRecordDTO) {
		var user = new User();
		BeanUtils.copyProperties(userRecordDTO, user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	
}
