package com.mirraw.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mirraw.entity.Cart;
import com.mirraw.entity.Category;
import com.mirraw.entity.Customer;
import com.mirraw.entity.Item;

public interface CartDao extends JpaRepository<Cart, Integer>{
	@Query(value="SELECT c.item From Cart c inner join c.customer cc inner join c.item  where  cc.email=:email ")
	public List<Item> findByCustomerEmail(String email);
	@Query(value="SELECT c.id From Cart c inner join c.customer cc inner join c.item i  where  cc.email=:email and i.itemName=:itemName ")
    public int findByCustomerEmailAndItemName(String email, String itemName);
//
//	public List<Cart> findByItemName(String itemName);
	
	public Cart findByCustomerAndItem(Customer customer, Item item);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM Cart WHERE customer_email=?1 AND item_name=?2",nativeQuery=true)
	public void deleteByCustomerEmailAndItemName(String email, String itemName);
	public List<Cart> findAllByCustomer(Customer c);
	





	//public Cart findByName(String name);

}
