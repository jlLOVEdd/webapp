package com.wdd.test.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wdd.test.bean.User;
import com.wdd.test.dao.BaseDao;
@Repository
public class UserDao extends BaseDao<User> {
	public List<User> getUserByname(String name){
		return this.getSqlSession().selectList(this.namespace+".getUserByname",name);
	}
}
