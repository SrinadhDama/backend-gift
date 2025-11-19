package com.example.ReactData.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ReactData.model.Login;
import com.example.ReactData.model.Register;
import com.example.ReactData.repo.LoginRepo;

@Service
public class LoginService {

	@Autowired
	LoginRepo loginRepo;
	
	// Return Register object instead of String
	@PostMapping("/login")
	public ResponseEntity<?> verifyLogin(@RequestBody Login login) {
	    Optional<Register> userOpt = loginRepo.findByUsernameAndPassword(login.getUsername(), login.getPassword());
	    if (userOpt.isPresent()) {
	        // userOpt.get().getId() is the 'id' from register table
	        return ResponseEntity.ok(userOpt.get()); 
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .body("Invalid UserName or Password");
	    }
	}
}
