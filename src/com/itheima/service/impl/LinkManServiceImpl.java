package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.LinkManDao;
import com.itheima.domain.LinkMan;
import com.itheima.service.LinkManService;
import com.itheima.utils.PageBean;

@Transactional
public class LinkManServiceImpl implements LinkManService {
	LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean=new PageBean<LinkMan>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		int totalCount=linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		double tc=totalCount;
		Double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		Integer begin=(currPage-1)*pageSize;
		List<LinkMan> list=linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		
		return linkManDao.findById(lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
}
