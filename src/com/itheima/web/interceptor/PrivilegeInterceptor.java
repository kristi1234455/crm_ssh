package com.itheima.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author jt
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 判断session中是否存在用户数据:
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		// 判断从session中获取的用户的信息是否为空:
		if(existUser == null){
			// 没有登录
			// 给出提示信息
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还没有登录！没有权限访问！");
			// 回到登录页面
			return actionSupport.LOGIN;
		}else{
			// 已经登录
			return invocation.invoke();
		}
	}

}
