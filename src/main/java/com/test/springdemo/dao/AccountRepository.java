package com.test.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.springdemo.entity.Account;


@Repository
public interface AccountRepository  extends JpaRepository<Account, Integer>{
	
	public Account findByUsername(String username);

}
