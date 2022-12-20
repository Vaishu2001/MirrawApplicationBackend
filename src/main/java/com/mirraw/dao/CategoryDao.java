package com.mirraw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mirraw.entity.Cart;
import com.mirraw.entity.Category;
import com.mirraw.entity.Customer;
import com.mirraw.entity.Item;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	
//	@Query("From Category c   where c.categoryName=:input")
	public List<Category> findByCategoryName( String categoryName);
//@Query("From Category c inner join c.item i where c.categoryName=:input  ")
	@Query(value="SELECT c.item From Category c where c.categoryName=:input ")
	public List<Item> getByCategoryName(String input);
	@Query(value="SELECT c.item From Category c where c.categoryName=:input ")
	public List<Item> SortByItemPriceDesc(String input);
	@Query(value="SELECT c.item From Category c where c.categoryName=:input ")
	public List<Item> SortByDiscount(String input);
	



	

}
