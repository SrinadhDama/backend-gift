package com.example.ReactData.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReactData.model.CartItem;

@Repository
public interface CartRepo extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findByGiftIdAndUserId(Long giftId, Long userId);
	
    List<CartItem> findByUserId(Long userId);
}
