package com.wdd.test.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdd.test.bean.User;
import com.wdd.test.dao.BaseDao;
import com.wdd.test.dao.user.UserDao;
import com.wdd.test.service.BaseService;
@Service
public class UserService extends BaseService<User>{
	@Autowired
	UserDao userDao;
	@Override
	public BaseDao<User> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	public User getUserByname(String name){
		List <User> list = userDao.getUserByname(name);
		User user =null;
		if(list!=null&&list.size()>0){
			user = list.get(0);
		}
		return user;		
	}
}
