package com.EKart.ProductService.inheritanceexamples.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User{
	
	long avgRating;

	public long getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(long avgRating) {
		this.avgRating = avgRating;
	}
	
	
}
