package com.wdd.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wdd.test.bean.User;
import com.wdd.test.dao.BaseDao;
import com.wdd.test.dao.LoginControllerDao;
@Service
public class LoginControllerService extends BaseService<User>  {
	
	@Autowired
	private LoginControllerDao loginControllerDao;
	
	public List<User> getpassWordByname(String username){
		return loginControllerDao.getPassWordByName(username);
	}

	@Override
	public BaseDao<User> getDao() {
		// TODO Auto-generated method stub
		return loginControllerDao;
	}
}
