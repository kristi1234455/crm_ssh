package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.SaleVisit;
import com.itheima.utils.PageBean;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

}
