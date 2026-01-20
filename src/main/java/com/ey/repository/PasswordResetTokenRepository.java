package com.ey.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.PasswordResetToken;
import com.ey.entity.User;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID>{
	Optional<PasswordResetToken> findByToken(String token);

    void deleteByUser(User user);
}
