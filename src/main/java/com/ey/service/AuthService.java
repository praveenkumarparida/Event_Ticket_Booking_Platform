package com.ey.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.LoginRequest;
import com.ey.dto.request.SignupRequest;
import com.ey.dto.response.AuthResponse;
import com.ey.dto.response.SignupResponse;
import com.ey.entity.PasswordResetToken;
import com.ey.entity.User;
import com.ey.exception.UserNotFoundException;
import com.ey.jwt.JwtUtil;
import com.ey.repository.PasswordResetTokenRepository;
import com.ey.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
    private CustomUserDetailsService userDetailsService;
	
	private Logger logger=LoggerFactory.getLogger(AuthService.class);

    public ResponseEntity<AuthResponse> login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        	logger.error("Invalid credentials");
            throw new RuntimeException("Invalid credentials");
        }

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getEmail());

        String token = jwtUtil.generateToken(userDetails);

        logger.info("User Logged in successfully");
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    public ResponseEntity<SignupResponse> signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
        	logger.warn("Email already exists");
            return ResponseEntity
                    .badRequest()
                    .body(new SignupResponse("Email already exists"));
        }

        String role = request.getRole().toUpperCase();

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        logger.info("User registered successfully");
        return ResponseEntity.ok(
                new SignupResponse("User registered successfully")
        );
    }
    
    public ResponseEntity<String> forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new UserNotFoundException("User not found");});

        tokenRepository.deleteByUser(user);

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryTime(LocalDateTime.now().plusMinutes(10));

        tokenRepository.save(resetToken);

        logger.info("Reset token is generated");
        return ResponseEntity.ok("RESET_TOKEN: " + token);
    }
    
    @Transactional
    public ResponseEntity<String> resetPassword(String token,String newPassword) {

        PasswordResetToken resetToken =tokenRepository.findByToken(token)
                        .orElseThrow(() -> {
                        	logger.error("Invalid token");
                        	return new RuntimeException("Invalid token");});

        if (resetToken.getExpiryTime().isBefore(LocalDateTime.now())) {
        	logger.warn("Token expired");
            return ResponseEntity.badRequest()
                    .body("Token expired");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));

        tokenRepository.delete(resetToken);

        logger.info("Password reset successfully");
        return ResponseEntity.ok("Password reset successfully");
    }
}

