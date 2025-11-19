package com.example.ReactData.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReactData.model.Gift;

@Repository
public interface GiftRepo extends JpaRepository<Gift, Long> {

}
