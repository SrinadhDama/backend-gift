package com.example.ReactData.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReactData.model.Gift;
import com.example.ReactData.repo.GiftRepo;

@Service
public class GiftService {

	@Autowired
	GiftRepo giftRepo;
	
	public List<Gift> getAllGifts(){
		return giftRepo.findAll();
	}
	
	public Gift addGift(Gift gift) {
		return giftRepo.save(gift);
	}
	
	public Optional<Gift> getGiftById(long id) {
        return giftRepo.findById(id);
    }
	public Gift updateGift(Long id, Gift updatedGift) {
	    return giftRepo.findById(id)
	        .map(existingGift -> {
	            existingGift.setName(updatedGift.getName());
	            existingGift.setCategory(updatedGift.getCategory());
	            existingGift.setDescription(updatedGift.getDescription());
	            existingGift.setPrice(updatedGift.getPrice());
	            existingGift.setImageUrl(updatedGift.getImageUrl());
	            return giftRepo.save(existingGift);
	        })
	        .orElseThrow(() -> new RuntimeException("Gift not found with id " + id));
	}
	
	public boolean deleteGift(Long id) {
	    if (giftRepo.existsById(id)) {
	        giftRepo.deleteById(id);
	        return true;
	    }
	    return false;
	}
}
