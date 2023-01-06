package com.mirraw.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;



import lombok.Data;

@Entity
@Data
@Table(name="Cart")
@Component
public class Cart {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int  id;
	//private String email;
	//private String itemName;
	//ivate int totalPrice;
	//private String itemUrl;
	private int quantity;
	@ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerEmail", referencedColumnName = "email")
    private Customer customer;
	
	@ManyToOne(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemName", referencedColumnName = "itemName")
    private Item item;
	
	

	
}
