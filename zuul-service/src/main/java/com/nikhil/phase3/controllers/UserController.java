package com.nikhil.phase3.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {
	
	private UserClient userClient;
	
	public UserController(UserClient userClient) {
		this.userClient = userClient;
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Object userDetails){
		return userClient.login(userDetails);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody Object userDetails){
		return userClient.signup(userDetails);
	}
}
