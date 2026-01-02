package com.nutricare.nutricare_advisor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.dto.AuthRequest;
import com.nutricare.nutricare_advisor.dto.AuthResponse;
import com.nutricare.nutricare_advisor.entity.Role;
import com.nutricare.nutricare_advisor.entity.User;
import com.nutricare.nutricare_advisor.repository.UserRepository;
import com.nutricare.nutricare_advisor.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository userRepo;
	private final PasswordEncoder encoder;
	private final JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.USER);
		userRepo.save(user);
		Map<String, String> response = new HashMap<>();
	    response.put("message", "User registered successfully");

	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody AuthRequest request) {
		User user=userRepo.findByEmail(request.getEmail())
				.orElseThrow();
		if(!encoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid Credentals!");
		}
		
		String token=jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		return new AuthResponse(token);
	}

}
