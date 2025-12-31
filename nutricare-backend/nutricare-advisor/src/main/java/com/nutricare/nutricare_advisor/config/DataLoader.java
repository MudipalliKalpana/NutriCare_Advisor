package com.nutricare.nutricare_advisor.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nutricare.nutricare_advisor.entity.Role;
import com.nutricare.nutricare_advisor.entity.User;
import com.nutricare.nutricare_advisor.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder encoder;
	
	@Override
	public void run(String...args) {
		if(userRepository.findByEmail("admin@nutricare.com").isEmpty()) {
			User admin=new User();
			admin.setName("System Admin");
			admin.setEmail("admin@nutricare.com");
			admin.setPassword(encoder.encode("admin@123"));
			admin.setRole(Role.ADMIN);
			
			userRepository.save(admin);
			System.out.println("✅ Default ADMIN created");
		}
	}
}
