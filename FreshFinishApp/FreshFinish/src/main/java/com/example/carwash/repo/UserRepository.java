package com.example.carwash.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carwash.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (for login)
    User findByEmail(String email);  // ✅ changed from Optional<User> to User

    User findByUsername(String username);

    List<User> findByRole(String role);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
