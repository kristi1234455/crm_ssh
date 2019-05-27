package com.itheima.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.SaleVisitDao;
import com.itheima.domain.SaleVisit;
import com.itheima.service.SaleVisitService;
import com.itheima.utils.PageBean;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean=new PageBean<SaleVisit>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount=saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		double tc=totalCount;
		Double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		Integer begin=(currPage-1)*pageSize;
		List<SaleVisit> list=saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}
	
	
}
