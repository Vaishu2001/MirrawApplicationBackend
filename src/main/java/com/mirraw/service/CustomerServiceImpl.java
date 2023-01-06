package com.mirraw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirraw.dao.CustomerDao;
import com.mirraw.entity.Customer;
import com.mirraw.exception.UserAlreadyExists;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public String saveSignUpdetails(Customer customer) throws UserAlreadyExists {
		// TODO Auto-generated method stub
		Customer customerdetails=findByName(customer.getEmail());
		
		if (customerdetails != null) {
			throw new UserAlreadyExists("User Already Exists");
		} else {
			customerDao.save(customer);
			return "sign up saved successfully";
		}
		
	}

	private Customer findByName(String email) {
		// TODO Auto-generated method stub
		return customerDao.findByEmail(email);
	}

	@Override
	public Customer findByUserNameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return customerDao.findByEmailAndPassword(email, password);
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return customerDao.findByEmail(email);
	}

}
