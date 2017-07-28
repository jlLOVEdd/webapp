package com.wdd.test.service.shiro;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.wdd.test.comm.util.MD5Password;

@Component
public class CustomCredentialsMatcher  extends SimpleCredentialsMatcher 
{
	@Resource
	private CacheManager  cacheManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info)
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Cache passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		boolean fal = false;
		try {
			
			 String username = (String) token.getPrincipal();
			 AtomicInteger retryCount = (AtomicInteger) passwordRetryCache.get(username);
		        if (retryCount == null) {
		            retryCount = new AtomicInteger(0);
		            passwordRetryCache.put(username, retryCount);
		        }
		        // 自定义一个验证过程：当用户连续输入密码错误5次以上禁止用户登录一段时间
		        if (retryCount.incrementAndGet() > 2) {
		            throw new ExcessiveAttemptsException();
		        }
		        fal = MD5Password.validPasswd(String.valueOf(token.getPassword()), getCredentials(info).toString());
		        if (fal) {
		            passwordRetryCache.remove(username);
		        }else{
		        	passwordRetryCache.put(username, retryCount);
		        }
		        return fal;
		    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fal;
	}

}
