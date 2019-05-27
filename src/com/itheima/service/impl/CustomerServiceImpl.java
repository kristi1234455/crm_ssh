package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.itheima.utils.PageBean;

import javassist.bytecode.annotation.DoubleMemberValue;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
		PageBean<Customer> pageBean=new PageBean<Customer>(); 
		pageBean.setCurrPage(currPage);//封装当前页数
		pageBean.setPageSize(pageSize);//封装每页显示记录数
		
		//封装总记录数
		Integer totalCount=customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		//封装总页数
		Double tc=totalCount.doubleValue();//将数据类型变成double类型
		Double num=Math.ceil(tc/pageSize);//使用math类中的向上取整方法ceil，前提是数据为double类型
		pageBean.setTotalPage(num.intValue());//将double类型的数据变成int类型，封装到pageBean中
		
		//封装每页查询到的数据集合
		Integer begin=(currPage-1)*pageSize;
		List<Customer> list=customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
}
