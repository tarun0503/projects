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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	@NotEmpty
	private String name;	
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String email;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;
	private String password_reset_token;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="user_role" , joinColumns = @JoinColumn(name="user_id"),
								inverseJoinColumns = @JoinColumn(name="role_id"))
	private Collection<Role> roles = new ArrayList<Role>();
}
