package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.dao.LinkManDao;
import com.itheima.domain.LinkMan;

public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {

//	public LinkManDaoImpl() {
//		super(LinkMan.class);
//	}
//
//	@Override
//	public Integer findCount(DetachedCriteria detachedCriteria) {
//		detachedCriteria.setProjection(Projections.rowCount());
//		List<Long> list=(List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
//		if(list.size() >0){
//			return list.get(0).intValue();
//		}
//		return 0;
//	}
//
//	@Override
//	public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
//		detachedCriteria.setProjection(null);
//		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
//		return list;
//	}

//	@Override
//	public void save(LinkMan linkMan) {
//		this.getHibernateTemplate().save(linkMan);
//	}

//	@Override
//	public LinkMan findById(Long lkm_id) {
//		
//		return this.getHibernateTemplate().get(LinkMan.class, lkm_id);
//	}

//	@Override
//	public void update(LinkMan linkMan) {
//		this.getHibernateTemplate().update(linkMan);
//	}
//
//	@Override
//	public void delete(LinkMan linkMan) {
//		this.getHibernateTemplate().delete(linkMan);
//	}
	
}
