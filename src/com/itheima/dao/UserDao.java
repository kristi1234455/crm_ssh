package com.itheima.dao;

import com.itheima.domain.User;

public interface UserDao extends BaseDao<User>{

	User login(User user);

	void regist(User user);

}
