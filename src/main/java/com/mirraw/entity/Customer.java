package com.mirraw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import lombok.Data;

@Entity
@Data
@Table(name="Customer")
@Component
public class Customer {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
}
