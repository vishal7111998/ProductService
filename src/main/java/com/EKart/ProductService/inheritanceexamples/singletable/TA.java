package com.EKart.ProductService.inheritanceexamples.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_ta")
@DiscriminatorValue(value = "1")
public class TA extends User{
	long rating;

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}
	
	
}
