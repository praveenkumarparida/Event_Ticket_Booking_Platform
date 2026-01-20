package com.ey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.entity.User;
import com.ey.repository.UserRepository;

@Service
public class ChangePasswordService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	private Logger logger=LoggerFactory.getLogger(ChangePasswordService.class);

    public ResponseEntity<String> changePassword(String email,String oldPassword,String newPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new RuntimeException("User not found");});

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
        	logger.error("Old password incorrect");
            return ResponseEntity.badRequest()
                    .body("Old password incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        
        logger.info("Password changed successfully");

        return ResponseEntity.ok("Password changed successfully");
    }
}
