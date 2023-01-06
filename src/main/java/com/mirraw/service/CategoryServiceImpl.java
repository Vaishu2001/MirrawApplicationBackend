package com.mirraw.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirraw.dao.CartDao;
import com.mirraw.dao.CategoryDao;
import com.mirraw.dao.CustomerDao;
import com.mirraw.dao.ItemDao;
import com.mirraw.entity.Cart;
import com.mirraw.entity.Category;
import com.mirraw.entity.Customer;
import com.mirraw.entity.Item;
import com.mirraw.exception.NoContentException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ItemDao itemDao;
	@Autowired
	CartDao cartDao;
	@Autowired
	Cart cart;
	@Autowired
	CustomerDao customerDao;

	@Override
	public String saveProduct(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
		return "Product saved successfully";
	}

	@Override
	public List<Category> getByCategoryName(String input) throws NoContentException {
		// TODO Auto-generated method stub
		List<Category> categories = categoryDao.findByCategoryName(input);
		if (categories.isEmpty()) {
			throw new NoContentException("All Categories are currently unavailable");
		} else {
			return categories;
		}

	}

//	@Override
//	public List<Item> filterByPrice(String input) {
//		// TODO Auto-generated method stub
//		 return categoryDao.findByPrice(input);
//		 
//	}
	@Override
	public List<Item> findByCategoryName(String input) {
		// TODO Auto-generated method stub
		List<Item> items = categoryDao.getByCategoryName(input);
		Comparator<Item> priceComparator = Comparator.comparing(Item::getItemPrice);
		List<Item> itemsSorted = (List<Item>) items.stream().sorted(priceComparator).collect(Collectors.toList());
		return itemsSorted;
	}

	@Override
	public List<Item> SortByPriceDesc(String input) {
		// TODO Auto-generated method stub
		List<Item> items = categoryDao.SortByItemPriceDesc(input);
		PriceDescComparator priceDesc = new PriceDescComparator();
		Collections.sort(items, priceDesc);
		return items;
	}

	@Override
	public List<Item> SortByItemDiscount(String input) {
		// TODO Auto-generated method stub
		List<Item> items = categoryDao.SortByDiscount(input);
		DiscountComparator discount = new DiscountComparator();
		Collections.sort(items, discount);
		return items;
	}

	@Override
	public List<Item> findByName(String itemName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveToCart(Cart cart) {
		// TODO Auto-generated method stub
		System.out.println(cart.getCustomer());
		cartDao.save(cart);
		return "Cart saved successfully";
	}

	@Override
	public Item findByItemName(String itemName) {
		// TODO Auto-generated method stub
		return itemDao.findByItemName(itemName);
	}

//	@Override
//	public Cart findByname(String name) {
//		// TODO Auto-generated method stub
//		return cartDao.findByName(name);
//	}

//	@Override
//	public String deleteCart(int id) {
//		// TODO Auto-generated method stub
//		cartDao.deleteById(id);
//		return "Deleted Successfully";
//	}
//
//	@Override
//	public List<Cart> getAllCart() throws NoContentException {
//		// TODO Auto-generated method stub
//		List<Cart> cart= cartDao.findAll();
//		if (cart.isEmpty()) {
//			throw new NoContentException("All Carts are currently unavailable");
//		} else {
//			return cart;
//		}
//	}

//	@Override
//	public List<Item> findByName(String itemName) {
//		// TODO Auto-generated method stub
//		return itemDao.findByName(itemName);
//	}
//
	@Override
	public List<Item> getAllCart(String email) throws NoContentException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		List<Item> cart = cartDao.findByCustomerEmail(email);
		System.out.println("hello");
		System.out.println(cart);

		if (cart.isEmpty()) {
			throw new NoContentException("All Carts are currently unavailable");
		} else {
			return cart;
		}

	}

	@Override
	public String updateCart(String email, String itemName, int quantity) {
		System.out.println(quantity);
		// TODO Auto-generated method stub
		// int cartId =cartDao.findByCustomerEmailAndItemName(email, itemName);
		// Optional<Cart> cart1=cartDao.findById(2);
		Customer customer = customerDao.findByEmail(email);
		System.out.println(customer);
		Item item = itemDao.findByItemName(itemName);
		System.out.println(item);
		Cart cartdata = cartDao.findByCustomerAndItem(customer, item);
		System.out.println(cartdata);

		cartdata.setQuantity(quantity);
		cartDao.save(cartdata);

		// System.out.println(cartdetails);
		// System.out.println(cart1);
		// System.out.println(cart);
		return "updated successfully";
	}

	@Override
	public Cart findByCustomerEmailAndItemName(Customer customer, Item item) {
		// TODO Auto-generated method stub
		return cartDao.findByCustomerAndItem(customer, item);
	}

	@Override
	public String deleteByCustomerEmailAndItemName(String email, String itemName) {
		// TODO Auto-generated method stub
		cartDao.deleteByCustomerEmailAndItemName(email, itemName);
		return "deleted successfully";
	}

	@Override
	public List<Cart> findAllByEmail(Customer c) {
		// TODO Auto-generated method stub
		return cartDao.findAllByCustomer(c);
	}

	@Override
	public String updateProduct(Category category) {
		// TODO Auto-generated method stub
		String name = category.getCategoryName();
		List<Category> categories = categoryDao.findByCategoryName(name);
		Category categ = categories.get(0);
		category.setId(categ.getId());
		List<Item> newItem = category.getItem();
		List<Item> item = categoryDao.getByCategoryName(name);
		for (int i = 0; i < item.size(); i++) {
			newItem.get(i).setId(item.get(i).getId());
		}
		categoryDao.save(category);
		return "updated successfully";
	}

//	@Override
//	public String deleteById(int id) {
//		// TODO Auto-generated method stub
//		categoryDao.deleteByCategoryId(id);
//		return "deleted successfully";
//	}

//	@Override
//	public Cart findByCustomerEmailAndItemName(String email, String itemName) {
//		// TODO Auto-generated method stub
//		return cartDao.findByCustomerEmailAndItemName(email, itemName);
//	}

//	@Override
//	public List<Cart> findByCartItemName(String itemName) {
//		// TODO Auto-generated method stub
//		return cartDao.findByItemName(itemName);
//	}

}
