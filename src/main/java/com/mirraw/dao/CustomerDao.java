package com.mirraw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mirraw.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	@Query("From Customer c where c.email=:email and c.password=:password")
	public Customer findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	public Customer findByEmail(String email);

}
