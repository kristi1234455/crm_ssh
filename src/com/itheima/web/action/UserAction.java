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
 * �û���Action����
 * @author jt
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	// ��������:
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	//userService����ʵ������ͨ��setUserService����ע���
	UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String regist(){
		userService.regist(user);
		return LOGIN;
	}

	/**
	 * �û���¼�ķ���:
	 */
	public String login(){
		System.out.println(user);
		// ����ҵ���:
		User existUser = userService.login(user);
		// ���ݽ��ҳ����ת��
		if(existUser == null){
			// ��¼ʧ��
			// ActionError��FieldError��ActionMessage
			this.addActionError("�û������������");
			return LOGIN;
		}else{
			// ��¼�ɹ�
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
