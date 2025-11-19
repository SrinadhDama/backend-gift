package com.example.ReactData.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReactData.model.Gift;
import com.example.ReactData.model.GiftMessage;
import com.example.ReactData.repo.GiftMessageRepo;

@Service
public class GiftMessageService {

	@Autowired
	GiftMessageRepo giftMessageRepo;
	
	public GiftMessage addGiftMessage(GiftMessage giftMessage) { 
		// Generate the personalized message
        String genMessage = "Happy " + giftMessage.getOccasion() + ", " + giftMessage.getName() + "!";
        giftMessage.setGeneratedMessage(genMessage);
		return giftMessageRepo.save(giftMessage);
	}
}
