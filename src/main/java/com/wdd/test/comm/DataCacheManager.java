package com.wdd.test.comm;


import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.wdd.test.bean.Role;
import com.wdd.test.service.role.RoleService;


@Component
public class DataCacheManager implements InitializingBean
{
	@Autowired
	private CacheManager  cacheManager;
	private Logger logger = Logger.getLogger(DataCacheManager.class);
	public  static final String ROLE ="roleInfo";
	@Autowired
	private RoleService roleService;
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		this.initRoles();
		//initpasscs();
	}
	public void initRoles(){
		List <Role> roleList =roleService.getRoles();
		Cache cache = cacheManager.getCache(ROLE);
		for (Role role : roleList) {
			cache.put(role.getId(), role);
		}
		logger.info("加载角色"+roleList.size()+"条");
	}
	public void initpasscs(){
		
		Cache cache = cacheManager.getCache("passwordRetryCache");
		cache.put("1", "我是shab");
		logger.info(cache.get("1"));
		
	}
}
