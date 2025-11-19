package com.example.ReactData.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReactData.model.CartItem;
import com.example.ReactData.model.Gift;
import com.example.ReactData.repo.CartRepo;
import com.example.ReactData.repo.GiftRepo;

@Service
public class CartService {
	
	@Autowired
	CartRepo cartRepo;

	@Autowired
	GiftRepo giftRepo;
	
	public void addToCart(long giftId, int quantity, long userId) {
	    Optional<CartItem> existing = cartRepo.findByGiftIdAndUserId(giftId,userId);
	    Gift gift = giftRepo.findById(giftId).orElseThrow();

	    if (existing.isPresent()) {
	        CartItem item = existing.get();
	        item.setQuantity(item.getQuantity() + quantity);
	        item.setName(gift.getName());
	        item.setDescription(gift.getDescription());
	        item.setImageUrl(gift.getImageUrl());
	        item.setPrice(gift.getPrice());
	        item.setUserId(userId); // <-- set userId
	        cartRepo.save(item);
	    } else {
	        CartItem item = new CartItem();
	        item.setGiftId(gift.getId());
	        item.setName(gift.getName());
	        item.setDescription(gift.getDescription());
	        item.setImageUrl(gift.getImageUrl());
	        item.setPrice(gift.getPrice());
	        item.setQuantity(quantity);
	        item.setUserId(userId); // <-- set userId
	        cartRepo.save(item);
	    }
	}

	
	public List<CartItem> getCartItems() {
        return cartRepo.findAll();
    }

	// Remove an item from a user's cart
    public void removeFromCart(Long giftId, Long userId) {
        cartRepo.findByGiftIdAndUserId(giftId, userId)
                .ifPresent(cartRepo::delete);
    }

    // Update item quantity for a user's cart
    public void updateQuantity(Long giftId, int quantity, Long userId) {
        cartRepo.findByGiftIdAndUserId(giftId, userId).ifPresent(item -> {
            item.setQuantity(quantity);
            cartRepo.save(item);
        });
    }

}
