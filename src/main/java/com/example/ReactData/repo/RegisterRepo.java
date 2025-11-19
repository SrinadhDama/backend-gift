package com.example.ReactData.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReactData.model.Register;

@Repository
public interface RegisterRepo extends JpaRepository<Register, Long> {

	Register findByUsername(String username);
	
}
