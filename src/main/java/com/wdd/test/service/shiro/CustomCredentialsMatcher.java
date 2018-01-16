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


public class CustomCredentialsMatcher  extends SimpleCredentialsMatcher 
{
	@Resource
	private CacheManager  cacheManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info)
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		boolean fal = false;
		try {
		        fal = MD5Password.validPasswd(String.valueOf(token.getPassword()), getCredentials(info).toString());
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
