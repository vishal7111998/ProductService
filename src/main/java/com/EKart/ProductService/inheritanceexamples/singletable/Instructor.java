package com.EKart.ProductService.inheritanceexamples.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User{
	
	String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
