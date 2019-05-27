package com.itheima.domain;

import java.util.Date;

/*CREATE TABLE `sale_visit` (
  `visit_id` bigint(255) NOT NULL AUTO_INCREMENT,
  `visit_time` date DEFAULT NULL, '拜访时间'
  `visit_addr` varchar(255) DEFAULT NULL, '拜访地点'
  `visit_detail` varchar(255) DEFAULT NULL, '拜访详情'
  `visit_nexttime` date DEFAULT NULL, '下次拜访时间'
  `visit_cust_id` bigint(20) DEFAULT NULL, '客户id'
  `visit_user_id` bigint(20) DEFAULT NULL, '负责人id'
  PRIMARY KEY (`visit_id`),
  KEY `FKgr4aivocixwcvkwxcmc0b4css` (`visit_cust_id`),
  KEY `FKc92iepd26mixxfiris92hccjx` (`visit_user_id`),
  CONSTRAINT `FKc92iepd26mixxfiris92hccjx` FOREIGN KEY (`visit_user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `FKgr4aivocixwcvkwxcmc0b4css` FOREIGN KEY (`visit_cust_id`) REFERENCES `cst_customer` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

*/

public class SaleVisit {
	private Long visit_id;
	private Date visit_time;
	private String visit_addr;
	private String visit_detail;
	private Date visit_nexttime;
	
	private Customer customer;//客户
	private User user;//业务员
	
	public Long getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(Long visit_id) {
		this.visit_id = visit_id;
	}
	public Date getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}
	public String getVisit_addr() {
		return visit_addr;
	}
	public void setVisit_addr(String visit_addr) {
		this.visit_addr = visit_addr;
	}
	public String getVisit_detail() {
		return visit_detail;
	}
	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}
	public Date getVisit_nexttime() {
		return visit_nexttime;
	}
	public void setVisit_nexttime(Date visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
