package com.example.ReactData.model;

import lombok.Data;

@Data
public class CartRequest {

	private long giftId;
    private int quantity;
    private Long userId;
}
