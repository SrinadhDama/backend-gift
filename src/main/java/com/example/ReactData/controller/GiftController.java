package com.example.ReactData.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.ReactData.model.Gift;
import com.example.ReactData.service.GiftService;

@RestController
@RequestMapping("/gifts")
@CrossOrigin(origins = "http://localhost:3000")
public class GiftController {

	@Autowired
	GiftService giftService;
	
	@GetMapping("/allgifts")
	public List<Gift> getGifts(){
		return giftService.getAllGifts();
	}
	
	@PostMapping("/addgift")
	public Gift insertGift(@RequestBody Gift gift) {
		return giftService.addGift(gift);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Gift> giftById(@PathVariable long id){
		Optional<Gift> gift = giftService.getGiftById(id);
		return gift.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Gift> updateGift(@PathVariable Long id, @RequestBody Gift updatedGift) {
	    Gift gift = giftService.updateGift(id, updatedGift);
	    return ResponseEntity.ok(gift);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteGift(@PathVariable Long id) {
	    boolean deleted = giftService.deleteGift(id);
	    if (deleted) {
	        return ResponseEntity.ok("Gift deleted successfully");
	    } else {
	        return ResponseEntity.status(404).body("Gift not found");
	    }
	}
}


