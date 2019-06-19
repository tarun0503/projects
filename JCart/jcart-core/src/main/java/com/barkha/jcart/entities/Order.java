package com.barkha.jcart.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false, unique=true)
	private String order_number;	
	
	@Column(name="created_on")
	private Date createdOn;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="cust_id")
	private Customer customer;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="delivery_addr_id")
	private Address deliveryAddress;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="billing_addr_id")
	private Address billingAddress;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="payment_id")
	private Payment payment;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="order")
	private Collection<OrderItem> items = new ArrayList<OrderItem>();
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
}
