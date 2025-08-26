package com.example.carwash.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private User partner;

    @ManyToOne
    private User center;

    private String vehicleType;
    private String washType;
    private LocalDateTime dateTime;
    private String status; // PENDING, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED
    private Double amount;
    private int rewardPoints;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }

    public User getPartner() { return partner; }
    public void setPartner(User partner) { this.partner = partner; }

    public User getCenter() { return center; }
    public void setCenter(User center) { this.center = center; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getWashType() { return washType; }
    public void setWashType(String washType) { this.washType = washType; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public int getRewardPoints() { return rewardPoints; }
    public void setRewardPoints(int rewardPoints) { this.rewardPoints = rewardPoints; }
}
