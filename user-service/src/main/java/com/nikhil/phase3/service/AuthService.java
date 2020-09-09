package com.nikhil.phase3.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikhil.phase3.dto.AuthenticationResponse;
import com.nikhil.phase3.dto.LoginRequest;
import com.nikhil.phase3.dto.RefreshTokenRequest;
import com.nikhil.phase3.dto.RegisterRequest;
import com.nikhil.phase3.model.NotificationEmail;
import com.nikhil.phase3.model.UserEntity;
import com.nikhil.phase3.model.VerificationToken;
import com.nikhil.phase3.repository.UserRepository;
import com.nikhil.phase3.repository.VerificationTokenRepository;
import com.nikhil.phase3.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

	public void signup(RegisterRequest registerRequest) {
		UserEntity user = new UserEntity();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);
		try {
			mailService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
					"Thank you for signing up!!, " + "please click on the below url to activate your account : "
							+ "http://localhost:8888/api/auth/accountVerification/" + token));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String generateVerificationToken(UserEntity user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	@Transactional(readOnly = true)
	public UserEntity getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userRepository.findByUsername(principal.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
	}

	private void fetchUserAndEnable(VerificationToken verificationToken) throws Exception {
		String username = verificationToken.getUser().getUsername();
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new Exception("User not found with name - " + username));
		user.setEnabled(true);
		userRepository.save(user);
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		try {
			fetchUserAndEnable(verificationToken.orElseThrow(() -> new Exception("Invalid Token")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = "";
		try {
			token = jwtProvider.generateToken(authenticate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AuthenticationResponse.builder().authenticationToken(token)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(loginRequest.getUsername()).build();
	}

	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		String token = "";
		try {
			token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AuthenticationResponse.builder().authenticationToken(token)
				.refreshToken(refreshTokenRequest.getRefreshToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(refreshTokenRequest.getUsername()).build();
	}

	public boolean isLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
}
