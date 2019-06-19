package com.barkha.jcart.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="permissions")
public class Permission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false, unique=true)
	private String name;	
	
	@Column(length=1024)
	private String description;
	
	@ManyToMany(mappedBy="permissions")
	private Collection<Role> roles = new ArrayList<Role>();
}
