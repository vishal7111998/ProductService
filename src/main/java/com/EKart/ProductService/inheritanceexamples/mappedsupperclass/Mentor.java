package com.EKart.ProductService.inheritanceexamples.mappedsupperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_mentor")
public class Mentor extends User{
	
	String companyName;
	double avgRating;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	
	
}
