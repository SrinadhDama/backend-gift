package com.example.ReactData.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ReactData.model.Register;
import com.example.ReactData.repo.RegisterRepo;

@Service
public class RegisterService {
	
	@Autowired
	RegisterRepo registerRepo;
	
	public ResponseEntity<String> registerDetails(Register register) {
		registerRepo.save(register);
		return ResponseEntity.ok("Registration successful");
	}

}
