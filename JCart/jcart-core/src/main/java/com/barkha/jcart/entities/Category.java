package com.barkha.jcart.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;	
	
	@Column(name="disp_order")
	private int displayOrder;
	
	
	private boolean disabled;
	
	@Column(length=1024)
	private String description;
	
	@OneToMany(mappedBy="category", cascade = {CascadeType.ALL})
	private Collection<Product> product = new ArrayList<Product>();
	
}
