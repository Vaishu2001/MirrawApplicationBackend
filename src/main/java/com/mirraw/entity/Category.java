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
@Table(name="Category")
@Component
public class Category {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int  id;
	private String categoryName;
	private String categoryUrl;
	
	@OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private List<Item> item;

}
