package com.mirraw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mirraw.entity.Customer;
import com.mirraw.entity.Item;

@Repository
public interface ItemDao extends JpaRepository<Customer, Integer>{
	@Query("From Item i where i.itemName =:itemName")
	public List<Item> findByName(String itemName);
	

}
