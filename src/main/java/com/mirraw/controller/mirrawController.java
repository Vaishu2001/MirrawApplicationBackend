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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirraw.dao.CartDao;
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
	Category category;
	@Autowired
	CategoryService categoryService;

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
//	theaterLogger.info(e.getMessage());
		}

		return responseEntity;
	}

	@GetMapping("allCart/{email}")
	public ResponseEntity<List<Cart>> GetAllCart(@PathVariable("email") String email) {
//    fetching the list of categories using the category field
		ResponseEntity<List<Cart>> responseEntity;

		try {
			responseEntity = new ResponseEntity<List<Cart>>(categoryService.getAllCart(email), HttpStatus.OK);
		} catch (NoContentException e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.METHOD_FAILURE);
//	theaterLogger.info(e.getMessage());
		}

		return responseEntity;
	}

	@PostMapping("/signUp")
	public ResponseEntity<String> savedetails(@RequestBody Customer customer) {
		ResponseEntity<String> responseEntity;
		try {
			responseEntity = new ResponseEntity<String>(customerService.saveSignUpdetails(customer), HttpStatus.OK);

		} catch (UserAlreadyExists e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			// theaterLogger.info(e.getCode()+" : "+e.getMessage());
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.METHOD_FAILURE);
			// theaterLogger.error(e.getMessage());
		}
		return responseEntity;
	}

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

	@PostMapping("/category")
	public String saveProduct(@RequestBody Category category) {

		return categoryService.saveProduct(category);
	}

//	@PostMapping("/cart")
//	public String saveToCart(@RequestBody Cart cart) {
//
//		return categoryService.saveToCart(cart);
//	}

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

	@GetMapping("itemPrice/{input}")
	public ResponseEntity<List<Item>> GetAllProductsByInput(@PathVariable("input") String input) {

		List<Item> item;
		ResponseEntity<List<Item>> responseEntity;

		item = categoryService.SortByPriceDesc(input);
		responseEntity = new ResponseEntity<List<Item>>(item, HttpStatus.OK);

		return responseEntity;
	}

	@GetMapping("itemDiscount/{input}")
	public ResponseEntity<List<Item>> GetAllProductsByDiscount(@PathVariable("input") String input) {

		List<Item> item;
		ResponseEntity<List<Item>> responseEntity;

		item = categoryService.SortByItemDiscount(input);
		responseEntity = new ResponseEntity<List<Item>>(item, HttpStatus.OK);

		return responseEntity;
	}

	@DeleteMapping("{email}/{name}")
	public ResponseEntity<String> DELETE(@PathVariable("email") String email, @PathVariable("name") String itemName) {
		/*
		 * Pseudo Code 1. Create Response Entity Object 2. Try deleting cart details
		 * through Service Implementation 3. if Successful send proper response
		 */
		ResponseEntity<String> responseEntity = null;
		Cart cartdetails = categoryService.findByEmailAndItemName(email, itemName);
		int id = -1;
		if (cartdetails != null) {
			id = cartdetails.getId();
		}
		if (id != -1) {
			String message = categoryService.deleteCart(id);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}

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

	@PostMapping("/cart")
	public String saveToCart(@RequestBody Cart cart) {
		String itemName = cart.getItemName();
		List<Item> item = categoryService.findByName(itemName);
		//check item exists in cart
		List<Cart> cartItem=categoryService.findByCartItemName(itemName);
		System.out.println(cartItem);
		System.out.println(cartItem.isEmpty());
		if(cartItem.isEmpty()) {
			return categoryService.saveToCart(cart);
		}
		else {
			return "Item already exists in the cart";
		}
	}
}
