package com.mirraw.entity;

import java.io.Serializable;

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
public class Customer implements Serializable{

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
}
