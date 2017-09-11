package com.wdd.test.dao;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtilsDao  {
	@Resource(name="redisTemplate")
	private RedisTemplate redisTemplate;
	
	public void get(){
		redisTemplate.opsForValue().get("user");
	}
}
