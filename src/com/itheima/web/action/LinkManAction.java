package com.itheima.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.LinkMan;
import com.itheima.service.CustomerService;
import com.itheima.service.LinkManService;
import com.itheima.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan=new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}
	
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private Integer currPage=1;
	private Integer pageSize=3;
	public void setCurrPage(Integer currPage) {
		if(currPage == null){
			currPage=1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize=3;
		}
		this.pageSize = pageSize;
	}
	
	 public String findAll(){
		 DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LinkMan.class);
		 if(linkMan.getLkm_name() != null){
			 detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		 }
		 if(linkMan.getLkm_gender() !=null && !"".equals(linkMan.getLkm_gender())){
			 detachedCriteria.add(Restrictions.eq("lkm_gender",linkMan.getLkm_gender()));
		 }
		 PageBean<LinkMan> pageBean= linkManService.findAll(detachedCriteria,currPage,pageSize);
		 ActionContext.getContext().getValueStack().push(pageBean);
		 return "findAll";
	 }
	 
	 private CustomerService customerService;
	 
	 public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public String saveUI(){
		List<Customer> list=customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	 }
	 
	 public String save(){
		 linkManService.save(linkMan);
		 return "saveSuccess";
	 }
	 public String edit(){
		 linkMan=linkManService.findById(linkMan.getLkm_id());
		 //方法一：手动push到值栈中；方法二：不手动push，jsp中采用model.name进行取值
		 ActionContext.getContext().getValueStack().push(linkMan);
		 //还需要回显出联系人所关联的所有客户的名字，便于重新进行选择
		 List<Customer> list=customerService.findAll();
		 ActionContext.getContext().getValueStack().set("list", list);
		 return "editSuccess";
	 }
	 public String update(){
		 linkManService.update(linkMan);
		 return "updateSuccess";
	 }
	 public String delete(){
		 linkMan=linkManService.findById(linkMan.getLkm_id());
		 linkManService.delete(linkMan);
		 return "deleteSuccess";
	 }
}
