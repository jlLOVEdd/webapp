package com.wdd.test.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdd.test.bean.Role;
import com.wdd.test.dao.BaseDao;
import com.wdd.test.dao.role.RoleDao;
import com.wdd.test.service.BaseService;
@Service
public class RoleService  extends BaseService<Role>{
	@Autowired
	private RoleDao roleDao;
	@Override
	public BaseDao<Role> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Role> getRoles(){
		return roleDao.getRoles();
	}
}
