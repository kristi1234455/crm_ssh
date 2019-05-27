package com.itheima.service;

import java.util.List;

import com.itheima.domain.User;

public interface UserService {

	User login(User user);

	void regist(User user);

	List<User> findAll();

}
