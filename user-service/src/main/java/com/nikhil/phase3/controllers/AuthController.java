package com.nikhil.phase3.controllers;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.phase3.dto.AuthenticationResponse;
import com.nikhil.phase3.dto.LoginRequest;
import com.nikhil.phase3.dto.RefreshTokenRequest;
import com.nikhil.phase3.dto.RegisterRequest;
import com.nikhil.phase3.service.AuthService;
import com.nikhil.phase3.service.RefreshTokenService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful", OK);
	}

	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successfully", OK);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
		return new ResponseEntity<AuthenticationResponse>(authService.login(loginRequest), HttpStatus.OK);
	}

	@PostMapping("/refresh/token")
	public ResponseEntity<AuthenticationResponse> refreshTokens(
			@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		return new ResponseEntity<AuthenticationResponse>(authService.refreshToken(refreshTokenRequest), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
	}
}
