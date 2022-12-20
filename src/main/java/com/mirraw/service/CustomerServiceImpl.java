package com.mirraw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirraw.dao.CustomerDao;
import com.mirraw.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public String saveSignUpdetails(Customer customer) {
		// TODO Auto-generated method stub
		
		customerDao.save(customer);
		return "sign up saved successfully";
	}

	@Override
	public Customer findByUserNameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return customerDao.findByEmailAndPassword(email, password);
	}

}
