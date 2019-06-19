package com.barkha.jcart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String address_line1;	
	private String address_line2;
	private String city;
	private String state;
	private String zip_code;
	private String country;
	
}
