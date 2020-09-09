package com.nikhil.phase3.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {
	
	@PostMapping("/users/login")
	public ResponseEntity<Object> login(@RequestBody Object userDetails);

	public ResponseEntity<Object> signup(Object userDetails);
}
