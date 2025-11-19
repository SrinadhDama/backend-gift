package com.example.ReactData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactData.model.GiftMessage;
import com.example.ReactData.service.GiftMessageService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/giftMessage")
public class GiftMessageController {
	
	@Autowired
	GiftMessageService giftMessageService;
	
	@PostMapping("/sendmessage")
	public GiftMessage sendMessage(@RequestBody GiftMessage giftMessage) {
		return giftMessageService.addGiftMessage(giftMessage);
		 
	}
	
	

}
