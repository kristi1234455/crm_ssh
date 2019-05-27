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
		pageBean.setCurrPage(currPage);//��װ��ǰҳ��
		pageBean.setPageSize(pageSize);//��װÿҳ��ʾ��¼��
		
		//��װ�ܼ�¼��
		Integer totalCount=customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		//��װ��ҳ��
		Double tc=totalCount.doubleValue();//���������ͱ��double����
		Double num=Math.ceil(tc/pageSize);//ʹ��math���е�����ȡ������ceil��ǰ��������Ϊdouble����
		pageBean.setTotalPage(num.intValue());//��double���͵����ݱ��int���ͣ���װ��pageBean��
		
		//��װÿҳ��ѯ�������ݼ���
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
