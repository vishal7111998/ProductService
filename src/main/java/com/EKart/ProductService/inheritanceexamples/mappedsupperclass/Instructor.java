package com.EKart.ProductService.inheritanceexamples.mappedsupperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_instructor")
public class Instructor extends User{
	
	String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
