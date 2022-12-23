package com.mirraw.service;

import java.util.List;

import com.mirraw.entity.Cart;
import com.mirraw.entity.Category;
import com.mirraw.entity.Customer;
import com.mirraw.entity.Item;
import com.mirraw.exception.NoContentException;

public interface CategoryService {

	public String saveProduct(Category category);

	public List<Category> getByCategoryName(String input) throws NoContentException;

	public List<Item> findByCategoryName(String input);

	public List<Item> SortByPriceDesc(String input);

	public List<Item> SortByItemDiscount(String input);

	public String saveToCart(Cart cart);

	//public Cart findByname(String name);

	public String deleteCart(int id);

	//public List<Cart> getAllCart() throws NoContentException;

	public List<Item> findByName(String itemName);

	public List<Cart> getAllCart(String email) throws NoContentException;

	public Cart findByEmailAndItemName(String email, String itemName);

	public List<Cart> findByCartItemName(String itemName);

	


	//public List<Item> filterByPrice(String input);



	

}
