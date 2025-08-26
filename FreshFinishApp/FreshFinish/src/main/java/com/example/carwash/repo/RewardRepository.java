package com.example.carwash.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carwash.model.Reward;
import com.example.carwash.model.User;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByUser(User user);
}
