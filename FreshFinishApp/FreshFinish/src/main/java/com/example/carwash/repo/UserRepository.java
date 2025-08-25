package com.example.carwash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carwash.model.User;


public interface UserRepository extends JpaRepository <User,Long> {
	 User findByEmail(String email);
}