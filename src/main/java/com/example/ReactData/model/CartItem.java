package com.example.ReactData.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long giftId;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int quantity;
    
    @Column(name = "user_id") // optional, ensures DB column name matches
    private Long userId;
    // Getters and Setters
}

