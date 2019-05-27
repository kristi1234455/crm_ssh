package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.LinkMan;
import com.itheima.utils.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
