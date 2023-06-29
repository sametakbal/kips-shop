package com.kips.backend.repository;

import com.kips.backend.domain.ConfirmationToken;
import com.kips.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationToken,Integer> {
    Optional<ConfirmationToken> findByTokenAndUser(String token, User user);
}

