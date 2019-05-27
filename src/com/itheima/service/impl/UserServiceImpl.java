package com.itheima.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.regist(user);
	}
	
	@Override
	public User login(User user) {
		//注册时的密码进行了加密，这里登陆时，需要先解密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}
