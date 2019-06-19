package com.barkha.jcart.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	private String name;	
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false, unique=true)
	private String sku;
	
	@Column(nullable=false)
	private BigDecimal price;
	
	private String image_url;
	private boolean disabled;
	
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	private Category category;
	
}
