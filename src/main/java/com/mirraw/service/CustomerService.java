package com.mirraw.service;

import com.mirraw.entity.Customer;

public interface CustomerService {

	public String saveSignUpdetails(Customer customer);

	public Customer findByUserNameAndPassword(String email, String password);

}
