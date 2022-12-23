package com.mirraw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirraw.entity.Cart;
import com.mirraw.entity.Category;

public interface CartDao extends JpaRepository<Cart, Integer>{

	public List<Cart> findByEmail(String email);

	public Cart findByEmailAndItemName(String email, String itemName);

	public List<Cart> findByItemName(String itemName);

	//public Cart findByName(String name);

}
