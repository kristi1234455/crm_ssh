package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Customer;
import com.itheima.utils.PageBean;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();

}
