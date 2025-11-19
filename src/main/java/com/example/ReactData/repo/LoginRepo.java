package com.example.ReactData.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReactData.model.Register;

@Repository
public interface LoginRepo extends JpaRepository<Register, Long> {

	Optional<Register> findByUsernameAndPassword(String username,String password);
}
