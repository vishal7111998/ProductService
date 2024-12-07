package com.EKart.ProductService.inheritanceexamples.mappedsupperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_ta")
public class TA extends User{
	long rating;

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}
	
	
}
