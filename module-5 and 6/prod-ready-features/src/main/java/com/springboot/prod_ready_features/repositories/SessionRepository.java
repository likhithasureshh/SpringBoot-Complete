package com.springboot.prod_ready_features.repositories;

import com.springboot.prod_ready_features.entities.Sessions;
import com.springboot.prod_ready_features.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Sessions,Long> {
    List<Sessions> findByUser(User user);

    Optional<Sessions> findByRefreshToken(String refreshToken);
}
