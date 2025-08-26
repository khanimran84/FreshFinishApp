package com.example.carwash.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carwash.model.Earning;
import com.example.carwash.model.User;

@Repository
public interface EarningRepository extends JpaRepository<Earning, Long> {
    List<Earning> findByPartner(User partner);
}
