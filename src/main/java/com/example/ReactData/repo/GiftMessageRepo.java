package com.example.ReactData.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReactData.model.GiftMessage;

@Repository
public interface GiftMessageRepo extends JpaRepository<GiftMessage, Long> {

}
