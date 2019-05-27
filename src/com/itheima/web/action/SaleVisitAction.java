package com.itheima.web.action;

import java.sql.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.SaleVisit;
import com.itheima.service.SaleVisitService;
import com.itheima.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	SaleVisit saleVisit = new SaleVisit();

	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	@Resource(name = "saleVisitService")
	private SaleVisitService saleVisitService;

	private Integer currPage = 1;
	private Integer pageSize = 3;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	private Date visit_end_time;

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	public Date getVisit_end_time() {
		return visit_end_time;
	}

	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// …Ë÷√Ãıº˛:
		if (saleVisit.getVisit_time() != null) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if (visit_end_time != null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	public String saveUI() {
		return "saveUI";
	}

	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
}
