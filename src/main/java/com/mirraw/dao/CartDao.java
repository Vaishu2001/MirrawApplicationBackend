package com.mirraw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirraw.entity.Cart;
import com.mirraw.entity.Category;

public interface CartDao extends JpaRepository<Cart, Integer>{

	//public Cart findByName(String name);

}
