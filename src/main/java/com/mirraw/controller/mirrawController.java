package com.mirraw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirraw.dao.CartDao;
import com.mirraw.dao.CategoryDao;
import com.mirraw.entity.Cart;
import com.mirraw.entity.Category;
import com.mirraw.entity.Customer;
import com.mirraw.entity.Item;
import com.mirraw.exception.NoContentException;
import com.mirraw.exception.UserAlreadyExists;
import com.mirraw.service.CategoryService;
import com.mirraw.service.CustomerService;

@RestController
@RequestMapping("/mirraw")
@CrossOrigin
public class mirrawController {
	@Autowired
	CustomerService customerService;
	@Autowired
	Customer customer;
	@Autowired
	Cart cart;
	@Autowired
	Category category;
	@Autowired
	CategoryService categoryService;

//Getting all the categories by giving the category name as input
	@GetMapping("category/{input}")
	public ResponseEntity<List<Category>> GetCategoryByName(@PathVariable("input") String input) {
//    fetching the list of categories using the category field
		ResponseEntity<List<Category>> responseEntity;
		try {
			responseEntity = new ResponseEntity<List<Category>>(categoryService.getByCategoryName(input),
					HttpStatus.OK);
		} catch (NoContentException e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.METHOD_FAILURE);

		}

		return responseEntity;
	}

//Getting all the carts by using email as input

	@GetMapping("allCart/{email}")
	public ResponseEntity<List<Item>> GetAllCart(@PathVariable("email") String email) {
//    fetching the list of categories using the category field
		ResponseEntity<List<Item>> responseEntity;

		try {
			responseEntity = new ResponseEntity<List<Item>>(categoryService.getAllCart(email), HttpStatus.OK);
		} catch (NoContentException e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.METHOD_FAILURE);

		}

		return responseEntity;
	}

//Saving the user details to the database

	@PostMapping("/signUp")
	public ResponseEntity<String> savedetails(@RequestBody Customer customer) {
		ResponseEntity<String> responseEntity;
		try {
			responseEntity = new ResponseEntity<String>(customerService.saveSignUpdetails(customer), HttpStatus.OK);

		} catch (UserAlreadyExists e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.METHOD_FAILURE);

		}
		return responseEntity;
	}

//Getting all the saved user details matching the respective email and password
	@GetMapping("findByUserNameAndPassword/{email}/{password}")
	public ResponseEntity<?> findByUserNameAndPassword(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		ResponseEntity<?> responseEntity;
		Customer customer = customerService.findByUserNameAndPassword(email, password);
		System.out.println(customer);
		if (customer != null) {
			responseEntity = new ResponseEntity("loggedIn", HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity("Customer does not exists", HttpStatus.BAD_REQUEST);
		}

		return responseEntity;

	}

	// Saving the category details to database

	@PostMapping("/category")
	public String saveProduct(@RequestBody Category category) {

		return categoryService.saveProduct(category);
	}

	// Updating the category details

	@PutMapping("/updatecategory")
	public String updateProduct(@RequestBody Category category) {

		return categoryService.updateProduct(category);
	}

//Getting the all the items belong to a particular category

	@GetMapping("item/{input}")
	public ResponseEntity<List<Item>> GetAllProducts(@PathVariable("input") String input) {
		/*
		 * Pseudo Code 1. Create Response Entity Object 2. Try find products by category
		 * through Service Implementation 3. if Successful send proper response
		 */

		List<Item> item;
		ResponseEntity<List<Item>> responseEntity;

		item = categoryService.findByCategoryName(input);
		responseEntity = new ResponseEntity<List<Item>>(item, HttpStatus.OK);

		return responseEntity;
	}
//Getting all the items with sorted price in descending order

	@GetMapping("itemPrice/{input}")
	public ResponseEntity<List<Item>> GetAllProductsByInput(@PathVariable("input") String input) {

		List<Item> item;
		ResponseEntity<List<Item>> responseEntity;

		item = categoryService.SortByPriceDesc(input);
		responseEntity = new ResponseEntity<List<Item>>(item, HttpStatus.OK);

		return responseEntity;
	}
//Getting all the products with sorted discounts in descending order

	@GetMapping("itemDiscount/{input}")
	public ResponseEntity<List<Item>> GetAllProductsByDiscount(@PathVariable("input") String input) {

		List<Item> item;
		ResponseEntity<List<Item>> responseEntity;

		item = categoryService.SortByItemDiscount(input);
		responseEntity = new ResponseEntity<List<Item>>(item, HttpStatus.OK);

		return responseEntity;
	}
//Deleting the cart details by using the email and name as input

	@DeleteMapping("{email}/{name}")
	public ResponseEntity<String> DELETE(@PathVariable("email") String email, @PathVariable("name") String itemName) {
		/*
		 * Pseudo Code 1. Create Response Entity Object 2. Try deleting cart details
		 * through Service Implementation 3. if Successful send proper response
		 */
		ResponseEntity<String> responseEntity = null;
		Customer customer = customerService.findByEmail(email);

		Item item = categoryService.findByItemName(itemName);
		Cart cartdetails = categoryService.findByCustomerEmailAndItemName(customer, item);
		System.out.println(cartdetails);
		int id = -1;
		if (cartdetails != null) {
			id = cartdetails.getId();
			System.out.println(id);
		}
		if (id != -1) {
			String message = categoryService.deleteByCustomerEmailAndItemName(email, itemName);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}

//Getting all the list of items by passing the item name as input

	@GetMapping("itemId/{name}")
	public ResponseEntity<List<Item>> GetItemById(@PathVariable("name") String itemName) {

		List<Item> item;
		ResponseEntity<List<Item>> responseEntity;

		item = categoryService.findByName(itemName);
		Item i = item.get(0);
		System.out.println(i.getId());
		responseEntity = new ResponseEntity<List<Item>>(item, HttpStatus.OK);

		return responseEntity;
	}

// Saving the cart details to the database
	@PostMapping("/cart/{email}/{itemName}")
	public String saveToCart(@PathVariable("email") String email, @PathVariable("itemName") String itemName) {

		Customer customer = customerService.findByEmail(email);
		System.out.println(customer);
		Item item = categoryService.findByItemName(itemName);
		System.out.println(item);
		Cart cart1 = new Cart();
		Cart cartdata = categoryService.findByCustomerEmailAndItemName(customer, item);
		System.out.println(cartdata);
		if (cartdata == null) {
			cart1.setCustomer(customer);
			cart1.setItem(item);
			cart1.setQuantity(1);
			System.out.println("if");
			return categoryService.saveToCart(cart1);

		} else {
			cartdata.setCustomer(cartdata.getCustomer());
			cartdata.setItem(cartdata.getItem());
			cartdata.setQuantity(1);
			System.out.println("else");
			return categoryService.saveToCart(cartdata);
		}

	}

//Updating the cart details 
	@PutMapping("/updateCart/{email}/{itemName}/{quantity}")
	public ResponseEntity<String> Update(@PathVariable("email") String email, @PathVariable("itemName") String itemName,
			@PathVariable("quantity") int quantity) {

		ResponseEntity<String> responseEntity;

		responseEntity = new ResponseEntity<String>(categoryService.updateCart(email, itemName, quantity),
				HttpStatus.OK);

		return responseEntity;
	}

//Getting all the cart details by passing the email as input
	@GetMapping("alCartByEmail/{email}")
	public ResponseEntity<List<Cart>> GetCartByEmail(@PathVariable("email") String email) {

		List<Cart> carts;
		ResponseEntity<List<Cart>> responseEntity;

		Customer c = customerService.findByEmail(email);

		responseEntity = new ResponseEntity<List<Cart>>(categoryService.findAllByEmail(c), HttpStatus.OK);

		return responseEntity;
	}

}
