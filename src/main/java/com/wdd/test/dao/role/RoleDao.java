package com.wdd.test.dao.role;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wdd.test.bean.Role;
import com.wdd.test.dao.BaseDao;

@Repository
public class RoleDao extends BaseDao<Role> {
	public List<Role>getRoles(){
		return this.getSqlSession().selectList(this.namespace+".getRoles");
	}
}
