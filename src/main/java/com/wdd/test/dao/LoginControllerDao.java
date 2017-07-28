package com.wdd.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wdd.test.bean.User;
@Repository
public class LoginControllerDao  extends BaseDao<User>{
	
	public  List<User> getPassWordByName(String username){
		System.out.println(this.namespace);
		return this.getSqlSession().selectList(this.namespace+".getPassWordByName",username);
	}
	
}
