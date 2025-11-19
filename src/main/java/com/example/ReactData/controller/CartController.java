package com.example.ReactData.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactData.model.CartItem;
import com.example.ReactData.model.CartRequest;
import com.example.ReactData.repo.CartRepo;
import com.example.ReactData.service.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
    private CartService cartService;
	
	@Autowired
    private CartRepo cartRepo;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartRequest request) {
        cartService.addToCart(request.getGiftId(), request.getQuantity(), request.getUserId());
        return ResponseEntity.ok("Gift added to cart");
    }

    
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        return ResponseEntity.ok(cartRepo.findByUserId(userId));
    }


    @DeleteMapping("/remove/{giftId}/{userId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable long giftId, @PathVariable Long userId) {
        cartService.removeFromCart(giftId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{giftId}")
    public ResponseEntity<Void> updateQuantity(@PathVariable long giftId, @RequestBody CartRequest request) {
        cartService.updateQuantity(giftId, request.getQuantity(), request.getUserId());
        return ResponseEntity.ok().build();
    }
}
