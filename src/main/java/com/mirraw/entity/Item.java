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
@Table(name="Item")
@Component
public class Item implements Serializable{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String itemName;
	private String itemUrl;
	private int itemPrice;
	private int markedPrice;
	private int discount;
	
}
