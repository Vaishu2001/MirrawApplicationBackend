package com.mirraw.service;

import com.mirraw.entity.Customer;
import com.mirraw.exception.UserAlreadyExists;

public interface CustomerService {

	public String saveSignUpdetails(Customer customer) throws UserAlreadyExists;

	public Customer findByUserNameAndPassword(String email, String password);

	public Customer findByEmail(String email);

}
