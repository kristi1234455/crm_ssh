package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

/**
 * 用户的Action的类
 * @author jt
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	// 接收数据:
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	//userService对象实际上是通过setUserService方法注入的
	UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String regist(){
		userService.regist(user);
		return LOGIN;
	}

	/**
	 * 用户登录的方法:
	 */
	public String login(){
		System.out.println(user);
		// 调用业务层:
		User existUser = userService.login(user);
		// 根据结果页面跳转：
		if(existUser == null){
			// 登录失败
			// ActionError、FieldError、ActionMessage
			this.addActionError("用户名或密码错误！");
			return LOGIN;
		}else{
			// 登录成功
//			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}
	}

	public String findAllUser() throws IOException{
		List<User> list = userService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
