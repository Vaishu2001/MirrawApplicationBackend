package com.mirraw.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private String name;
	private String price;
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name = "customerid" )
//	private Customer customer;
	

}
