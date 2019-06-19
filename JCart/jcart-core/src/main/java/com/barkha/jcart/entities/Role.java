package com.barkha.jcart.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;	
	
	@Column(length=1024)
	private String description;
	
	@ManyToMany(mappedBy="roles")
	private Collection<User> user = new ArrayList<User>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="role_permission" , joinColumns = @JoinColumn(name="role_id"),
								inverseJoinColumns = @JoinColumn(name="permission_id"))
	private Collection<Permission> permissions = new ArrayList<Permission>();
}

