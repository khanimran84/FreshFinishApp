package com.example.carwash.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carwash.model.Booking;
import com.example.carwash.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(User customer);
    List<Booking> findByPartner(User partner);
    List<Booking> findByCenter(User center);

    // For analytics
    long countByStatus(String status);
}
