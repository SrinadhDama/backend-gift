	package com.example.ReactData.model;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import lombok.Data;
	
	@Entity
	@Table(name="gifts")
	@Data
	public class Gift {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	
	    private String name;
	    private String category;
	    private String description;
	    private double price;
	    private String imageUrl;
	
	}
